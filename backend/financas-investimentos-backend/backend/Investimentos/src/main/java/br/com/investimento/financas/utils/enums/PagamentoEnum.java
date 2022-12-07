package br.com.investimento.financas.utils.enums;

import lombok.Getter;
import lombok.Setter;

public enum PagamentoEnum {

    DINHEIRO(0,"Dinheiro"),
    CREDITO(1,"Cartão de crédito"),
    DEBITO(2,"Cartão de débito"),
    CHEQUE(3,"Pagamento com cheque"),
    BOLETO(4,"Boleto"),
    PAGAMENTO(5,"Pagamento salário"),
    PIX(6,"Transferência pix");

    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private Integer codigo;

    private PagamentoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
