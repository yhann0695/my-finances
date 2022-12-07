package br.com.investimento.financas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.investimento.financas.model.Categoria;
import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.utils.enums.PagamentoEnum;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDto {

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
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

    public LancamentoDto(Lancamento entidade) {
        this.id = entidade.getId();
        this.descricao = entidade.getDescricao();
        this.usuario = new UsuarioDto(entidade.getUsuario());
        this.categoria = new CategoriaDto(entidade.getCategoria());
        this.formaPagamento = entidade.getFormaPagamento();
        this.valor = entidade.getValor();
        this.dataCadastro = entidade.getDataCadastro();
        this.observacao = entidade.getObservacao();
        this.tipo = entidade.getTipo();
        this.nomeArquivo = entidade.getNomeArquivo();
    }

    public static List<LancamentoDto> listToDto(List<Lancamento> lancamentos) {
        return lancamentos.stream().map(LancamentoDto::new).collect(Collectors.toList());
    }
}
