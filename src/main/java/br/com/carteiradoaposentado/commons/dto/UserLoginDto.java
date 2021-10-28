package br.com.carteiradoaposentado.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserLoginDto implements Serializable {

    private final String email;
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
