package br.com.investimento.financas.security;

import br.com.investimento.financas.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class LoggedUser implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private Role role;

    private Collection<? extends GrantedAuthority> roles;

    public LoggedUser(Usuario usuario) {
        this.usuario = usuario;

        Role role;

        if (usuario instanceof Usuario) {
            role = Role.USUARIO;
        } else {
            throw new IllegalStateException("O tipo de usuário não é válido");
        }

        this.role = role;
        this.roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

//    @Secured("ROLE_USUARIO")
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return super.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getValue().toUpperCase()))
//                .collect(Collectors.toList());
//    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
