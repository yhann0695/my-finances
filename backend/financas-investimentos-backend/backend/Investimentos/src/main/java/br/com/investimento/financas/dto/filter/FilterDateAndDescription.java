package br.com.investimento.financas.dto.filter;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class FilterDateAndDescription {

    private int page;
    private int size;
    private Long usuario;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFim;
    private String descricao;
    private Pageable pageable;
}
