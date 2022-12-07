package br.com.investimento.financas.builder;

import br.com.investimento.financas.dto.CategoriaDto;
import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.dto.UsuarioDto;
import br.com.investimento.financas.model.Categoria;
import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.utils.enums.PagamentoEnum;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class LancamentoDtoBuilder {

    private LocalDate dataCadastro;
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private PagamentoEnum formaPagamento;
    private CategoriaDto categoria;
    private TipoLancamento tipo;
    private String observacao;
    private UsuarioDto usuario;
    private String nomeArquivo;


    public LancamentoDto umLancamentoDto() {
        return new LancamentoDto(
                this.dataCadastro = LocalDate.now(),
                this.id = 1L,
                this.descricao = "Teste",
                this.valor = BigDecimal.valueOf(100),
                this.formaPagamento = PagamentoEnum.DINHEIRO,
                this.categoria = new CategoriaDto(getCategoria()),
                this.tipo = TipoLancamento.DESPESA,
                this.observacao = "Teste",
                this.usuario = new UsuarioDto(getUsuario()),
                this.nomeArquivo = "teste.pdf"
        );

    }

    public static Usuario getUsuario() {
        return new Usuario(1L, "teste.@gmail.com", "Sobrenome", "teste", "teste", "123", new ArrayList<Lancamento>());
    }

    public static Categoria getCategoria() {
        return new Categoria(1L, "Teste", getUsuario(), new ArrayList<Lancamento>());
    }
}
