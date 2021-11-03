package br.com.carteiradoaposentado.infra.util;

import br.com.carteiradoaposentado.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class UtilJwt {

    private static Long EXPIRATION = 10800000L;
    private static String SECRET = "Ax41_$kjlkj@hhduLKDJAL57/a4541SSjjkia41L5874aStrf";

    public static String createToken(final Authentication authenticate) {
        final User usuario = (User) authenticate.getPrincipal();
        final Date hoje = new Date();
        final Date expiracao = new Date(hoje.getTime() + EXPIRATION);
        return Jwts.builder()
                .setIssuer("API Carteira do Aposentado")
                .setSubject(usuario.getId())
                .setIssuedAt(hoje)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
