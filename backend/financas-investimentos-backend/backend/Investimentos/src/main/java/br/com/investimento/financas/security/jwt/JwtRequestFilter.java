package br.com.investimento.financas.security.jwt;

import br.com.investimento.financas.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            boolean isTokenValido = this.jwtService.isTokenValido(token);

            if (isTokenValido) {
                UserDetails usuarioAutenticado = this.userDetailsService
                        .loadUserByUsername(this.jwtService.obterLoginUsuario(token));

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                        usuarioAutenticado,
                        null,
                        usuarioAutenticado.getAuthorities()
                );

                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(user);

            }
        }

        filterChain.doFilter(request, response);
    }
}
