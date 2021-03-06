package br.com.carteiradoaposentado.infra.util.jwt;

import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.UtilDate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.StringJoiner;

import static br.com.carteiradoaposentado.commons.constantes.CDA.QTD_PARANS_TOKEN;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public final class Token implements Serializable {

    private static final long serialVersionUID = -2368910770584694714L;

    private Token() { }

    public static final LocalDateTime JWT_TOKEN_VALIDITY = now().plusHours(3);
    public static final String SECRET = "javaword";

    private static void checkGerar(final String idUsuario, final Long idPerfil, final LocalDateTime dataExpiracao) {
        StringJoiner check = new StringJoiner(" ");
        if (idUsuario == null) {
            check.add("[ idUsuario ]");
        }

        if (idPerfil == null) {
            check.add("[ idPerfil ]");
        }

        if (!UtilDate.isValida(dataExpiracao)) {
            check.add("[ dataExpiracao ]");
        }

        if (check.length() > 0) {
            throw new IllegalArgumentException(String.format("Faltou definir arg(s): %s para gerar Token", check));
        }
    }

    private static void checkGerar(final String idUsuario, final Long idPerfil) {
        StringJoiner check = new StringJoiner(" ");
        if (idUsuario == null) {
            check.add("[ idUsuario ]");
        }

        if (idPerfil == null) {
            check.add("[ idPerfil ]");
        }

        if (check.length() > 0) {
            throw new IllegalArgumentException(String.format("Faltou definir arg(s): %s para gerar Token", check));
        }
    }

    public static Optional<String> gerar(
            final String idUsuario,
            final Long idPerfil,
            final LocalDateTime dataExpiracao
    ) {
        checkGerar(idUsuario, idPerfil, dataExpiracao);
        return Optional.ofNullable(Jwts.builder()
                .setSubject(createSubject(idUsuario, idPerfil))
                .setExpiration(UtilDate.toDate(dataExpiracao))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact());
    }

    public static Optional<String> gerar(final String idUsuario, final Long idPerfil) {
        checkGerar(idUsuario, idPerfil);
        return Optional.ofNullable(Jwts.builder()
                .setSubject(createSubject(idUsuario, idPerfil))
                .setExpiration(UtilDate.toDate(JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact());
    }

    private static String createSubject(final String idUsuario, final Long idPerfil) {
        return String.format("%s;%s;%s", idUsuario, idPerfil, LocalDate.now());
    }

    public static Boolean isValid(final String token) {
        try {
            if (StringUtils.isBlank(token)) {
                return false;
            }
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String getUserId(final String token) {
        try {
            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (StringUtils.isBlank(subject)) {
                throw new BussinesException("N??o foi possivei recuperar informa????es do token!");
            }
            String[] decode = subject.split(";");
            return String.valueOf(decode[0]);
        } catch (Exception e) {
            throw new BussinesException("N??o foi possivei recuperar informa????es do token! " + e.getMessage());
        }
    }

    public static String getUserId() {
        try {
            final HttpServletRequest request = getCurrentHttpRequest();
            final String possivelToken = request.getHeader("Authorization");
            if (isEmpty(possivelToken) || !possivelToken.startsWith("Bearer ")) {
                throw new BussinesException("N??o foi possivei recuperar informa????es do token!");
            }
            final String token = possivelToken.substring(7);
            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (StringUtils.isBlank(subject)) {
                throw new BussinesException("N??o foi possivei recuperar informa????es do token!");
            }
            String[] decode = subject.split(";");
            return String.valueOf(decode[0]);
        } catch (Exception e) {
            throw new BussinesException("N??o foi possivei recuperar informa????es do token! " + e.getMessage());
        }

    }

//    public static Long getUserPerfil(final String token) {
//        try {
//            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
//            if (StringUtils.isBlank(subject)) {
//                throw new BussinesException("N??o foi possivei recuperar informa????es do token!");
//            }
//            String[] decode = subject.split(";");
//            return Long.valueOf(decode[1]);
//        } catch (Exception e) {
//            throw new BussinesException("N??o foi possivei recuperar informa????es do token! " + e.getMessage());
//        }
//
//    }

    public static Optional<Token.Value> decode(final String token) {
        if (StringUtils.isBlank(token)) {
            return Optional.empty();
        } else {
            try {
                String subject = ((Claims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()).getSubject();
                if (StringUtils.isBlank(subject)) {
                    return Optional.empty();
                } else {
                    String[] decode = subject.split(";");
                    return ArrayUtils.getLength(decode)
                            != QTD_PARANS_TOKEN.getValor() ? Optional.empty()
                            : Optional.of(Token.Value.newInstance(String.valueOf(decode[0]), Long.valueOf(decode[1])));
                }
            } catch (ExpiredJwtException var3) {
                return Optional.of(Token.Value.newInstance("ACESSO EXPIROU - NECESSARIO NOVA AUTENTICACAO"));
            } catch (UnsupportedJwtException var4) {
                return Optional.empty();
            } catch (SignatureException | IllegalArgumentException | MalformedJwtException var5) {
                return Optional.of(Token.Value.newInstance("TOKEN INVALIDO - CONTATE O DEPTO DE TI"));
            }
        }

    }


    public interface Value {
        String getIdUsuario();

        Long getIdPerfil();

        String getMsgInconsistencia();

        static Token.Value newInstance(final String idUsuario, final Long idPerfil, final String msgInconsistencia) {
            return new Token.Value() {
                public String getIdUsuario() {
                    return idUsuario;
                }

                public Long getIdPerfil() {
                    return idPerfil;
                }

                public String getMsgInconsistencia() {
                    return msgInconsistencia;
                }
            };
        }

        static Token.Value newInstance(final String idUsuario, final Long idPerfil) {
            return newInstance(idUsuario, idPerfil, (String) null);
        }

        static Token.Value newInstance(final String mensagem) {
            return newInstance((String) null, (Long) null, mensagem);
        }
    }

    private static HttpServletRequest getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .orElseThrow(() -> new BussinesException("N??o foi possivel recuperar request"));
    }
}
