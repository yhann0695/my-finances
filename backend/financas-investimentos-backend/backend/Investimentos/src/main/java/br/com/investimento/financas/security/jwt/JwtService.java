package br.com.investimento.financas.security.jwt;

import br.com.investimento.financas.dto.UsuarioDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {

        String gerarToken(UsuarioDto usuario);

        Claims obterClaims(String token) throws ExpiredJwtException;

        boolean isTokenValido(String token);

        String obterLoginUsuario(String token);
}
