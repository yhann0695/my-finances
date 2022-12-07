package br.com.investimento.financas.dto.estatistica;

import java.math.BigDecimal;

import br.com.investimento.financas.model.Categoria;
import br.com.investimento.financas.utils.DateUtils;
import br.com.investimento.financas.utils.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosEstatisticaMesAnteriorDto {

    private String nomeCategoria;
    private BigDecimal totalLancamentos;

    public DadosEstatisticaMesAnteriorDto(Categoria categoria) {
        this.nomeCategoria = categoria.getNome();
        this.totalLancamentos = categoria.getLancamentos().stream().map(lancamento -> 
            DateUtils.isMesAnterior(lancamento.getDataCadastro()) 
            && TipoLancamento.DESPESA.equals(lancamento.getTipo()) 
                ?  lancamento.getValor() 
                : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
}