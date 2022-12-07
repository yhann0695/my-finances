package br.com.investimento.financas.model;

import br.com.investimento.financas.dto.UsuarioDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "EMAIL_USUARIO", unique = true)
    private String email;

    @Column(name = "SENHA_USUARIO")
    private String password;

    @Column(name = "LOGIN_USUARIO", unique = true)
    private String username;

    @Column(name = "NOME_USUARIO")
    private String nome;

    @Column(name = "SOBRENOME_USUARIO")
    private String sobrenome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Lancamento> lancamentos;

    public Usuario(UsuarioDto usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.username = usuario.getUsername();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
    }
}
