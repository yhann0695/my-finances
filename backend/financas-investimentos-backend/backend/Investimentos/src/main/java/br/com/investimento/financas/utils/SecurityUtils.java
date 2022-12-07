package br.com.investimento.financas.utils;

import br.com.investimento.financas.security.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }

    public static Long loggedUsuario() {
        LoggedUser loggedUser = loggedUser();

        if(loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado");
        }

        return loggedUser.getUsuario().getId();
    }

    public static String usernameLoggedUsuario() {
        LoggedUser loggedUser = loggedUser();

        if(loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado");
        }

        return loggedUser.getUsuario().getUsername();
    }

    public static String passwordLoggedUsuario() {
        LoggedUser loggedUser = loggedUser();

        if(loggedUser == null) {
            throw new IllegalStateException("Não existe um usuário logado");
        }

        return loggedUser.getUsuario().getPassword();
    }
}
