package br.com.investimento.financas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.investimento.financas.model.Usuario;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public UsuarioDto(Usuario entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.sobrenome = getSobrenome();
        this.email = entidade.getEmail();
        this.username = entidade.getUsername();
        this.password = entidade.getPassword();
    }
}
