package br.com.investimento.financas.dto.filter;

import lombok.Getter;

@Getter
public class FilterReleasesByCategory {

    private Long usuario;
    private int page;
    private int size;
    private Long categoria;
}
