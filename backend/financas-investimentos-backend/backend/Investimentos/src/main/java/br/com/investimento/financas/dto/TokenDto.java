package br.com.investimento.financas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {

    private String nome;
    private Long id;
    private String token;
}
