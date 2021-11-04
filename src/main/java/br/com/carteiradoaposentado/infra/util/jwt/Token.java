package br.com.carteiradoaposentado.infra.util.jwt;

import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.UtilDate;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.StringJoiner;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class Token implements Serializable {

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

    public static Optional<String> gerar(final String idUsuario, final Long idPerfil, final LocalDateTime dataExpiracao) {
        checkGerar(idUsuario, idPerfil, dataExpiracao);
        return Optional.ofNullable(Jwts.builder().setSubject(createSubject(idUsuario, idPerfil)).setExpiration(UtilDate.toDate(dataExpiracao)).signWith(SignatureAlgorithm.HS256, SECRET).compact());
    }

    public static Optional<String> gerar(final String idUsuario, final Long idPerfil) {
        checkGerar(idUsuario, idPerfil);
        return Optional.ofNullable(Jwts.builder().setSubject(createSubject(idUsuario, idPerfil)).setExpiration(UtilDate.toDate(JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS256, SECRET).compact());
    }

    private static String createSubject(final String idUsuario, final Long idPerfil) {
        return String.format("%s;%s;%s", idUsuario, idPerfil, LocalDate.now());
    }

    public static Boolean isValid(final String token) {
        try {
            if (StringUtils.isBlank(token)) return false;
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static String getUserId(final String token){
        try {
            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (StringUtils.isBlank(subject)) throw new BussinesException("Não foi possivei recuperar informações do token!");
            String[] decode = subject.split(";");
            return String.valueOf(decode[0]);
        }catch (Exception e){
            throw new BussinesException("Não foi possivei recuperar informações do token!");
        }
    }

    public static String getUserId(final HttpServletRequest request){
        try {
            final String token = request.getHeader("Authorization");
            if (isEmpty(token) || !token.startsWith("Bearer ")) throw new BussinesException("Não foi possivei recuperar informações do token!");
            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (StringUtils.isBlank(subject)) throw new BussinesException("Não foi possivei recuperar informações do token!");
            String[] decode = subject.split(";");
            return String.valueOf(decode[0]);
        }catch (Exception e){
            throw new BussinesException("Não foi possivei recuperar informações do token!");
        }


    }

    public static Long getUserPerfil(final String token){
        try {
            String subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (StringUtils.isBlank(subject)) throw new BussinesException("Não foi possivei recuperar informações do token!");
            String[] decode = subject.split(";");
            return Long.valueOf(decode[1]);
        }catch (Exception e){
            throw new BussinesException("Não foi possivei recuperar informações do token!");
        }

    }

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
                    return ArrayUtils.getLength(decode) != 3 ? Optional.empty() : Optional.of(Token.Value.newInstance(String.valueOf(decode[0]), Long.valueOf(decode[1])));
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
}
