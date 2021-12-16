package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.Carteira;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CarteiraAtualDto implements Serializable {

    private final Set<Carteira.PorcentagemTipo> porcentagemTipo;
    private final Set<Carteira.PorcentagemCategoria> porcentagemCategoria;
    private final Set<Carteira.PorcentagemSetor> porcentagemSetor;
    private final Set<Carteira.TipoQtds> tipoQtds;
    private final Map<String, BigDecimal> valorPorAtivo;
    private final BigDecimal patrimonio;


    public CarteiraAtualDto(final Builder builder) {
        this.porcentagemTipo = builder.porcentagemTipo;
        this.porcentagemCategoria = builder.porcentagemCategoria;
        this.porcentagemSetor = builder.porcentagemSetor;
        this.tipoQtds = builder.tipoQtds;
        this.valorPorAtivo = builder.valorPorAtivo;
        this.patrimonio = builder.patrimonio;
    }

    @JsonProperty("porcentagemTipo")
    public Set<Carteira.PorcentagemTipo> getPorcentagemTipo() {
        return porcentagemTipo;
    }

    @JsonProperty("porcentagemCategoria")
    public Set<Carteira.PorcentagemCategoria> getPorcentagemCategoria() {
        return porcentagemCategoria;
    }

    @JsonProperty("porcentagemSetor")
    public Set<Carteira.PorcentagemSetor> getPorcentagemSetor() {
        return porcentagemSetor;
    }

    @JsonProperty("tipoQtds")
    public Set<Carteira.TipoQtds> getTipoQtds() {
        return tipoQtds;
    }

    @JsonProperty("valorPorAtivo")
    public Map<String, BigDecimal> getValorPorAtivo() {
        return valorPorAtivo;
    }

    @JsonProperty("patrimonio")
    public BigDecimal getPatrimonio() {
        return patrimonio;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Builder {

        private Set<Carteira.PorcentagemTipo> porcentagemTipo;
        private Set<Carteira.PorcentagemCategoria> porcentagemCategoria;
        private Set<Carteira.PorcentagemSetor> porcentagemSetor;
        private Set<Carteira.TipoQtds> tipoQtds;
        private Map<String, BigDecimal> valorPorAtivo;
        private BigDecimal patrimonio;

        public Builder() {
            this.porcentagemTipo = null;
            this.porcentagemCategoria = null;
            this.porcentagemSetor = null;
            this.tipoQtds = null;
            this.valorPorAtivo = null;
            this.patrimonio = null;
        }

        public Builder comPorcentagemTipo(final Set<Carteira.PorcentagemTipo> value) {
            this.porcentagemTipo = value;
            return this;
        }

        public Builder comPorcentagemCategoria(final Set<Carteira.PorcentagemCategoria> value) {
            this.porcentagemCategoria = value;
            return this;
        }

        public Builder comPorcentagemSetor(final Set<Carteira.PorcentagemSetor> value) {
            this.porcentagemSetor = value;
            return this;
        }

        public Builder comTipoQtds(final Set<Carteira.TipoQtds> value) {
            this.tipoQtds = value;
            return this;
        }

        public Builder comValorPorAtivo(final Map<String, BigDecimal> value) {
            this.valorPorAtivo = value;
            return this;
        }

        public Builder comPatrimonio(final BigDecimal value) {
            this.patrimonio = value;
            return this;
        }

        public CarteiraAtualDto build() {
            return new CarteiraAtualDto(this);
        }
    }
}
