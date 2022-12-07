package br.com.investimento.financas.service;

import br.com.investimento.financas.builder.LancamentoDtoBuilder;
import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.exception.NegocioException;
import br.com.investimento.financas.repository.LancamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LancamentoServiceTest {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Test
    void deveCadastrarUmLancamento() {
        LancamentoDtoBuilder builder  = new LancamentoDtoBuilder();
        LancamentoDto lancamentoDto = builder.umLancamentoDto();
        lancamentoService.cadastrar(lancamentoDto);

        assertEquals(1, lancamentoDto.getId());
    }

    @Test
    void deveRetornarAoCadastrarLancamentoUmNegocioException() {
        LancamentoDtoBuilder builder  = new LancamentoDtoBuilder();
        LancamentoDto lancamentoDto = builder.umLancamentoDto();

        lancamentoDto.setDescricao("");
        lancamentoDto.setTipo(null);

        assertThrows(NegocioException.class, () -> lancamentoService.cadastrar(lancamentoDto));
    }

    @Test
    void deveAlterarUmLancamento() {
        LancamentoDtoBuilder builder  = new LancamentoDtoBuilder();
        LancamentoDto lancamentoDto = builder.umLancamentoDto();

        lancamentoService.cadastrar(lancamentoDto);

        lancamentoDto.setDescricao("Alterado");
        lancamentoService.alterar(1L, lancamentoDto);

        assertEquals("Alterado", lancamentoRepository.findById(lancamentoDto.getId()).get().getDescricao());
    }

    @Test
    void deveListarOs5UltimosLancamentos() {
        LancamentoDtoBuilder builder  = new LancamentoDtoBuilder();
        LancamentoDto lancamento1 = builder.umLancamentoDto();
        LancamentoDto lancamento2 = builder.umLancamentoDto();
        LancamentoDto lancamento3 = builder.umLancamentoDto();
        LancamentoDto lancamento4 = builder.umLancamentoDto();
        LancamentoDto lancamento5 = builder.umLancamentoDto();

        List<LancamentoDto> lancamentos = Arrays.asList(
                lancamento1, lancamento2, lancamento3,
                lancamento4, lancamento5
        );

        for (LancamentoDto lancamentoDto : lancamentos) {
            lancamentoService.cadastrar(lancamentoDto);
        }

        assertEquals(5, lancamentoRepository.findTop5ByUsuarioOrderByIdDesc(LancamentoDtoBuilder.getUsuario()).size());
    }

    @Test
    void deveRemoverUmLancamento() {
        LancamentoDtoBuilder builder  = new LancamentoDtoBuilder();
        LancamentoDto lancamentoDto = builder.umLancamentoDto();

        lancamentoService.cadastrar(lancamentoDto);

        lancamentoService.excluir(1L);

        assertNull(lancamentoRepository.findById(lancamentoDto.getId()).orElse(null));
    }
}
