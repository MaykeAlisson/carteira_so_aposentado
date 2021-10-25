package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserCreateDto implements Serializable {

    private final String nome;
    private final String email;
    private final String senha;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JsonCreator
    public UserCreateDto(
            @JsonProperty("nome") final String nome,
            @JsonProperty("email") final String email,
            @JsonProperty("senha") final String senha
    ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto that = (UserCreateDto) o;
        return Objects.equals(nome, that.nome) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MÉTODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static User fromUser(final UserCreateDto userCreate){

        // TODO codificar senha

       return new User(userCreate.getNome(), userCreate.getSenha(), userCreate.getEmail(), LocalDateTime.now());

    }

    public static User updateData( User newUser, final UserCreateDto newInfo){

        newUser.setNome(newInfo.getNome());
        newUser.setEmail(newInfo.getEmail());
        newUser.setSenha(newInfo.getSenha()); // TODO codificar senha

        return newUser;

    }
}
