package br.com.investimento.financas.dto.relatorio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.investimento.financas.model.Lancamento;
import br.com.investimento.financas.utils.DateUtils;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DadosRelatorioLancamentosMensaisDto {

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    private Date data;

    private String descricao;
    private TipoLancamento tipo;
    private BigDecimal valor;
    
    DadosRelatorioLancamentosMensaisDto(Lancamento entidade) {
        this.descricao = entidade.getDescricao();
        this.tipo = entidade.getTipo();
        this.data = DateUtils.convertLocalDateToDate(entidade.getDataCadastro());
        this.valor = entidade.getValor();
    }

    public static List<DadosRelatorioLancamentosMensaisDto> converter(List<Lancamento> entidades) {
        return entidades.stream().map(DadosRelatorioLancamentosMensaisDto::new).collect(Collectors.toList());
    }
    
}