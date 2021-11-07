package br.com.carteiradoaposentado.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoginDto implements Serializable {

    @NotNull @Email
    private final String email;
    @NotNull @Length(min = 6)
    private final String senha;

    @JsonCreator
    public UserLoginDto(
            @JsonProperty("email") final String email,
            @JsonProperty("senha") final String senha
    ) {
        this.email = email;
        this.senha = senha;
    }

    public static UsernamePasswordAuthenticationToken convert(final UserLoginDto dto) {
        return new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
