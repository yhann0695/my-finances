package br.com.investimento.financas.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.investimento.financas.dto.CategoriaDto;
import br.com.investimento.financas.dto.estatistica.DadosEstatisticaMensalDto;
import br.com.investimento.financas.dto.estatistica.DadosEstatisticaMesAnteriorDto;
import br.com.investimento.financas.exception.NegocioException;
import br.com.investimento.financas.exception.NotFoundException;
import br.com.investimento.financas.model.Categoria;
import br.com.investimento.financas.repository.CategoriaRepository;
import br.com.investimento.financas.repository.LancamentoRepository;
import br.com.investimento.financas.utils.SecurityUtils;
import br.com.investimento.financas.utils.constants.Constants;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public void cadastrar(CategoriaDto dto) {
        Categoria entidade = new Categoria(dto);
        if (existeCategoria(dto, entidade))
            throw new NegocioException(Constants.CATEGORIA_EXISTENTE);

        this.categoriaRepository.save(entidade);
    }

    private boolean existeCategoria(CategoriaDto dto, Categoria entidade) {
        return this.categoriaRepository.findByNome(entidade.getNome()) != null
                && SecurityUtils.loggedUsuario() == dto.getId();
    }

    public List<DadosEstatisticaMensalDto> listarDespesaMensalTotalPorCategoria(Long usuarioId) {
        List<Categoria> categorias = categoriaRepository.findAllByUsuarioId(usuarioId);
        return categorias.stream().map(DadosEstatisticaMensalDto::new).collect(Collectors.toList());
    }

    public List<DadosEstatisticaMesAnteriorDto> listarDespesaMensalAnteriorTotalPorCategoria(Long usuarioId) {
        List<Categoria> categorias = categoriaRepository.findAllByUsuarioId(usuarioId);
        return categorias.stream().map(DadosEstatisticaMesAnteriorDto::new).collect(Collectors.toList());
    }

    public void remover(Long categoriaId) {
        Categoria categoria = this.pegarCategoria(categoriaId);
        this.existeRelacionamento(categoriaId);
        this.categoriaRepository.delete(categoria);
    }

    private void existeRelacionamento(Long categoriaId) {
        if (this.lancamentoRepository.isExisteRelacionamento(categoriaId))
            throw new NegocioException(Constants.EXISTE_RELACAO);
    }

    private Categoria pegarCategoria(Long categoriaId) {
        return this.categoriaRepository
                .findById(categoriaId)
                .orElseThrow(() -> new NegocioException(Constants.CATEGORIA_INEXISTENTE));
    }

    public List<CategoriaDto> listar(Long usuarioId) {
        return this.categoriaRepository.findAllByUsuarioId(usuarioId)
                .stream()
                .map(CategoriaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Page<CategoriaDto> consultarPaginado(int page, int size, String nome, Long usuarioId) {
        Pageable paginacao = PageRequest.of(page, size);
        Page<Categoria> lista = pegarCategoriasPorUsuario(nome, paginacao, usuarioId);
        return new PageImpl<>(CategoriaDto.listToDto(lista.getContent()), paginacao, lista.getTotalElements());
    }

    private Page<Categoria> pegarCategoriasPorUsuario(String nome, Pageable paginacao, Long usuarioId) {
        Page<Categoria> lista;
        if (nome == null)
            lista = this.categoriaRepository.findAllByUsuarioIdOrderByNomeAsc(paginacao, usuarioId);
        else
            lista = categoriaRepository.findAllByUsuarioIdAndNomeContainingIgnoreCaseOrderByNomeAsc(paginacao, usuarioId, nome);

        if (lista.isEmpty()) throw new NotFoundException(Constants.MSG_NENHUM_REGISTRO_ENCONTRADO);

        return lista;
    }
}
