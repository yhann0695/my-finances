package br.com.investimento.financas.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.investimento.financas.dto.UsuarioDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtImpl implements JwtService{

    @Value("${jwt.expiracao}")
    private String expiracao;

    @Value("${jwt.chave-assinaturo}")
    private String chaveAssinatura;

    @Override
    public String gerarToken(UsuarioDto usuario) {
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(Long.valueOf(this.expiracao));
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setExpiration(data)
                .setSubject(usuario.getUsername())
                .claim("nome", usuario.getNome())
                .claim("id", usuario.getId())
                .claim("tempo", dataHoraExpiracao.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                .signWith(SignatureAlgorithm.HS512, this.chaveAssinatura)
                .compact();
    }

    @Override
    public Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(this.chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValido(String token) {
        try {
            LocalDateTime dataExpiracao = this.obterClaims(token)
                    .getExpiration()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return !LocalDateTime.now().isAfter(dataExpiracao);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public String obterLoginUsuario(String token) {
        return this.obterClaims(token).getSubject();
    }
}
