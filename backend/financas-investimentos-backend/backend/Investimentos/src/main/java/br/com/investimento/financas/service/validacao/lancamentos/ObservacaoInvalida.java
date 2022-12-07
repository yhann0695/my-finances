package br.com.investimento.financas.service.validacao.lancamentos;

import br.com.investimento.financas.dto.LancamentoDto;
import br.com.investimento.financas.exception.NegocioException;
import br.com.investimento.financas.service.validacao.Validacoes;
import br.com.investimento.financas.utils.constants.Constants;
import org.springframework.stereotype.Component;

@Component
public class ObservacaoInvalida implements Validacoes {

    @Override
    public void validar(LancamentoDto dto) {
        if(dto.getObservacao().length() > 50) {
            throw new NegocioException(Constants.OBSERVACAO_INVALIDA);
        }
    }
}
