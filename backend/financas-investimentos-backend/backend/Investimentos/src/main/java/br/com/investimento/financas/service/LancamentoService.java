package br.com.investimento.financas.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import br.com.investimento.financas.dto.filter.FilterDateAndDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.exception.FileException;
import br.com.investimento.financas.exception.NotFoundException;
import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.repository.LancamentoRepository;
import br.com.investimento.financas.service.validacao.Validacoes;
import br.com.investimento.financas.utils.ArquivoUtils;
import br.com.investimento.financas.utils.SecurityUtils;
import br.com.investimento.financas.utils.constants.Constants;
import br.com.investimento.financas.utils.enums.TipoLancamento;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private List<Validacoes> validacoes;

    public void cadastrar(LancamentoDto dto) {
        this.validarDados(dto);
        Lancamento entidade = new Lancamento(dto);
        entidade.setDataCadastro(LocalDate.now());

        lancamentoRepository.save(entidade);
    }

    private void validarDados(LancamentoDto dto) {
        this.validacoes.forEach(v -> v.validar(dto));
    }

    @Transactional
    public Page<LancamentoDto> consultarPaginado(FilterDateAndDescription filter) {
        Pageable paginacao = PageRequest.of(filter.getPage(), filter.getSize());
        Page<Lancamento> lista = lancamentoRepository
                .findAllByUsuarioIdAndDataCadastroBetweenOrDescricaoContainingIgnoreCase(
                        paginacao,
                        filter.getUsuario(),
                        filter.getDataInicio(),
                        filter.getDataFim(),
                        filter.getDescricao()
                );

        verificarLista(lista.isEmpty());

        return new PageImpl<>(LancamentoDto.listToDto(lista.getContent()), paginacao, lista.getTotalElements());
    }

    public Page<LancamentoDto> listarLancamentosPorCategorias(int page, int size, Long categoriaId, Long usuarioId) {
        Pageable paginacao = PageRequest.of(page, size);
        Page<Lancamento> lista = this.lancamentoRepository.findAllByCategoriaIdAndUsuarioId(
                paginacao,
                categoriaId,
                usuarioId
        );
        return new PageImpl<>(LancamentoDto.listToDto(lista.getContent()), paginacao, lista.getTotalElements());
    }

    public List<LancamentoDto> listar() {
        List<Lancamento> lancamentos = lancamentoRepository.findTop5ByUsuarioOrderByIdDesc(buscarUsuarioLogado());
        verificarLista(lancamentos.isEmpty());
        return LancamentoDto.listToDto(lancamentos);
    }

    private void verificarLista(boolean lancamentos) {
        if (lancamentos)
            throw new NotFoundException(Constants.MSG_NENHUM_REGISTRO_ENCONTRADO);
    }

    private Usuario buscarUsuarioLogado() {
        return usuarioService.buscarPorId(SecurityUtils.loggedUsuario());
    }

    public void alterar(Long id, LancamentoDto dto) {
        this.buscarLancamento(id);
        this.validarDados(dto);
        Lancamento entidade = new Lancamento(dto);
        lancamentoRepository.save(entidade);
    }

    public void uploadFile(MultipartFile file) {
		try {
			ArquivoUtils.writeFile(file);
		} catch (IOException e) {
			throw new FileException("Ocorreu um erro ao fazer upload do arquivo", e);
		}
	}

	public ByteArrayResource download(String nomeArquivo) {
		ByteArrayResource resource = null;
		try {
			resource = ArquivoUtils.readFile(nomeArquivo);
		} catch (IOException e) {
			throw new FileException("Ocorreu um erro ao fazer download do arquivo", e);
		}
		return resource;
	}

    public BigDecimal obterSaldoMensalTotalPorUsuario(Long usuario) {
        BigDecimal receitas = this.obterSaldoMensalPorTipo(TipoLancamento.RECEITA, usuario, LocalDate.now());
        BigDecimal desepesas = this.obterSaldoMensalPorTipo(TipoLancamento.DESPESA, usuario, LocalDate.now());

        if(receitas == null) receitas = BigDecimal.ZERO;
        if(desepesas == null) desepesas = BigDecimal.ZERO;

        return receitas.subtract(desepesas);
    }

    public BigDecimal obterSaldoMensalPorTipo(TipoLancamento tipo, Long usuario, LocalDate data) {
        return lancamentoRepository.obterSaldoUsuario(usuario, tipo, data);
    }

    public void excluir(Long id) {
        Lancamento lancamento = buscarLancamento(id);
        lancamentoRepository.delete(lancamento);
    }

    private Lancamento buscarLancamento(Long id) {
        return lancamentoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.LANCAMENTO_NAO_ENCONTRADO));
    }
}
