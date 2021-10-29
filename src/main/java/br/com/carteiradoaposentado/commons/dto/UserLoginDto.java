package br.com.carteiradoaposentado.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoginDto implements Serializable {

    @NotNull(message = "email obrigatorio!") @NotEmpty(message = "email obrigatorio!")
    private final String email;
    @NotNull(message = "senha obrigatorio!") @NotEmpty(message = "senha obrigatorio!")
    private final String senha;

    @JsonCreator
    public UserLoginDto(
            @JsonProperty("email") final String email,
            @JsonProperty("senha") final String senha
    ) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
