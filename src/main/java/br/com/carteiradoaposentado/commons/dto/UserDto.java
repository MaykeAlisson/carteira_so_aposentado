package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    @NotNull(message = "id obrigatorio!") @NotEmpty(message = "id obrigatorio!")
    private String id;
    @NotNull(message = "nome obrigatorio!") @NotEmpty(message = "nome obrigatorio!")
    private String nome;
    @NotNull(message = "email obrigatorio!") @NotEmpty(message = "email obrigatorio!")
    private String email;

    public UserDto(){}

    public UserDto(User user){
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MÉTODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
