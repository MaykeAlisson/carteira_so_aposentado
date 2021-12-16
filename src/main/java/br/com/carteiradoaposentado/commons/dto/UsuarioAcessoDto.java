package br.com.carteiradoaposentado.commons.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UsuarioAcessoDto implements Serializable {

    @NotNull
    private final String idUser;
    @NotNull
    private final String nome;
    @NotNull
    private final String token;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public UsuarioAcessoDto(Builder build) {
        this.idUser = build.idUser;
        this.nome = build.nome;
        this.token = build.token;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getIdUser() {
        return idUser;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Builder {
        private String idUser;
        private String nome;
        private String token;

        public Builder() {
            this.idUser = null;
            this.nome = null;
            this.token = null;
        }

        public Builder comIdUser(final String value) {
            this.idUser = value;
            return this;
        }

        public Builder comNome(final String value) {
            this.nome = value;
            return this;
        }

        public Builder comToken(final String value) {
            this.token = value;
            return this;
        }

        public UsuarioAcessoDto build() {
            return new UsuarioAcessoDto(this);
        }
    }
}
