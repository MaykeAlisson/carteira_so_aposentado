package br.com.carteiradoaposentado.domain;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.json.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Document(collection = "carteira")
public class Carteira implements Serializable {

    @Id
    private String id;
    private String idUsuario;
    private Set<PorcentagemTipo> porcentagemTipo;
    private Set<PorcentagemCategoria> porcentagemCategoria;
    private Set<PorcentagemSetor> porcentagemSetor;
    private Set<TipoQtds> tipoQtds;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JsonCreator
    public Carteira(
            @JsonProperty("id") String id,
            @JsonProperty("idUsuario") String idUsuario,
            @JsonProperty("porcentagemTipo") Set<PorcentagemTipo> porcentagemTipo,
            @JsonProperty("porcentagemCategoria") Set<PorcentagemCategoria> porcentagemCategoria,
            @JsonProperty("porcentagemSetor") Set<PorcentagemSetor> porcentagemSetor,
            @JsonProperty("tipoQtds") Set<TipoQtds> tipoQtds)
    {
        this.id = id;
        this.idUsuario = idUsuario;
        this.porcentagemTipo = porcentagemTipo;
        this.porcentagemCategoria = porcentagemCategoria;
        this.porcentagemSetor = porcentagemSetor;
        this.tipoQtds = tipoQtds;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS / SETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Set<PorcentagemTipo> getPorcentagemTipo() {
        return porcentagemTipo;
    }

    public void setPorcentagemTipo(Set<PorcentagemTipo> porcentagemTipo) {
        this.porcentagemTipo = porcentagemTipo;
    }

    public Set<PorcentagemCategoria> getPorcentagemCategoria() {
        return porcentagemCategoria;
    }

    public void setPorcentagemCategoria(Set<PorcentagemCategoria> porcentagemCategoria) {
        this.porcentagemCategoria = porcentagemCategoria;
    }

    public Set<PorcentagemSetor> getPorcentagemSetor() {
        return porcentagemSetor;
    }

    public void setPorcentagemSetor(Set<PorcentagemSetor> porcentagemSetor) {
        this.porcentagemSetor = porcentagemSetor;
    }

    public Set<TipoQtds> getTipoQtds() {
        return tipoQtds;
    }

    public void setTipoQtds(Set<TipoQtds> tipoQtds) {
        this.tipoQtds = tipoQtds;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EQUALS & HASCODE
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carteira carteira = (Carteira) o;
        return Objects.equals(id, carteira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class PorcentagemTipo{
        private final Tipo tipo;
        private final BigDecimal porcentagem;

        @JsonCreator
        public PorcentagemTipo(
                @JsonProperty("tipo") @JsonDeserialize(using = TipoAtivoDeserialize.class) Tipo tipo,
                @JsonProperty("porcentagem") BigDecimal porcentagem)
        {
            this.tipo = tipo;
            this.porcentagem = porcentagem;
        }

        @JsonProperty("tipo")
        @JsonSerialize(using = TipoAtivoSerializer.class)
        public Tipo getTipo() {
            return tipo;
        }

        @JsonProperty("porcentagem")
        public BigDecimal getPorcentagem() {
            return porcentagem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PorcentagemTipo that = (PorcentagemTipo) o;
            return tipo == that.tipo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tipo);
        }
    }

    public static class PorcentagemCategoria{
        private final Categoria categoria;
        private final BigDecimal porcentagem;

        @JsonCreator
        public PorcentagemCategoria(
                @JsonProperty("categoria") @JsonDeserialize(using = CategoriaAtivoDeserialize.class) Categoria categoria,
                @JsonProperty("porcentagem") BigDecimal porcentagem
        ) {
            this.categoria = categoria;
            this.porcentagem = porcentagem;
        }

        @JsonProperty("categoria")
        @JsonSerialize(using = CategoriaAtivoSerializer.class)
        public Categoria getCategoria() {
            return categoria;
        }

        @JsonProperty("porcentagem")
        public BigDecimal getPorcentagem() {
            return porcentagem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PorcentagemCategoria that = (PorcentagemCategoria) o;
            return categoria == that.categoria;
        }

        @Override
        public int hashCode() {
            return Objects.hash(categoria);
        }
    }

    public static class PorcentagemSetor{
        private final Setor setor;
        private final BigDecimal porcentagem;

        @JsonCreator
        public PorcentagemSetor(
                @JsonProperty("setor")  @JsonDeserialize(using = SetorAtivoDeserialize.class) Setor setor,
                @JsonProperty("porcentagem") BigDecimal porcentagem
        ) {
            this.setor = setor;
            this.porcentagem = porcentagem;
        }

        @JsonProperty("setor")
        @JsonSerialize(using = SetorAtivoSerializer.class)
        public Setor getSetor() {
            return setor;
        }

        @JsonProperty("porcentagem")
        public BigDecimal getPorcentagem() {
            return porcentagem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PorcentagemSetor that = (PorcentagemSetor) o;
            return setor == that.setor;
        }

        @Override
        public int hashCode() {
            return Objects.hash(setor);
        }
    }

    public static class TipoQtds {
        private final Tipo tipo;
        private final Long qtd;

        @JsonCreator
        public TipoQtds(
                @JsonProperty("tipo") @JsonDeserialize(using = TipoAtivoDeserialize.class) Tipo tipo,
                @JsonProperty("qtd") Long qtd
        ) {
            this.tipo = tipo;
            this.qtd = qtd;
        }

        @JsonProperty("tipo")
        @JsonSerialize(using = TipoAtivoSerializer.class)
        public Tipo getTipo() {
            return tipo;
        }

        @JsonProperty("qtd")
        public Long getQtd() {
            return qtd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TipoQtds tipoQtds = (TipoQtds) o;
            return tipo == tipoQtds.tipo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tipo);
        }
    }
}
