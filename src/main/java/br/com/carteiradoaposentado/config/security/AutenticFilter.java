package br.com.carteiradoaposentado.config.security;

import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class AutenticFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public AutenticFilter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = getToken(request);
        if (Token.isValid(token)) {
            autenticarUsuario(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(final String token) {
        final String userId = Token.getUserId(token);
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Usuario com o id %s não encontrado!", userId)));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {

        final String token = request.getHeader("Authorization");
        if (isEmpty(token) || !token.startsWith("Bearer ")) return null;
        return token.substring(7);
    }
}
