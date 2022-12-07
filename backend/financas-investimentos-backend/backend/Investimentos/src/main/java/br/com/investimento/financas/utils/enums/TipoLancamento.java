package br.com.investimento.financas.utils.enums;

import lombok.Getter;

public enum TipoLancamento {

    RECEITA("Receita"),
    DESPESA("Despesa");

    @Getter
    private final String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }
}
