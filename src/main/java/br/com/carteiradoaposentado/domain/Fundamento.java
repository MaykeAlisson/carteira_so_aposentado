package br.com.carteiradoaposentado.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Fundamento implements Serializable {

    //https://blog.genialinvestimentos.com.br/indicadores-fundamentalistas/

    private final Double pL;
    private final Double pVPA;
    private final Double dY;
    private final Double rOE;
    private final Double ebitda;
    private final Double dividaBrutaPatrimonioLiquido;
    private final Long notaGovernaca;
    private final LocalDateTime data;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Fundamento(
            final Double pL,
            final Double pVPA,
            final Double dY,
            final Double rOE,
            final Double ebitda,
            final Double dividaBrutaPatrimonioLiquido,
            final Long notaGovernaca,
            final LocalDateTime data
    ) {
        this.pL = pL;
        this.pVPA = pVPA;
        this.dY = dY;
        this.rOE = rOE;
        this.ebitda = ebitda;
        this.dividaBrutaPatrimonioLiquido = dividaBrutaPatrimonioLiquido;
        this.notaGovernaca = notaGovernaca;
        this.data = data;
    }

    public Fundamento(Builder builder){
        this.pL = builder.pL;
        this.pVPA = builder.pVPA;
        this.dY = builder.dY;
        this.rOE = builder.rOE;
        this.ebitda = builder.ebitda;
        this.dividaBrutaPatrimonioLiquido = builder.dividaBrutaPatrimonioLiquido;
        this.notaGovernaca = builder.notaGovernaca;
        this.data = builder.data;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Double getpL() {
        return pL;
    }

    public Double getpVPA() {
        return pVPA;
    }

    public Double getdY() {
        return dY;
    }

    public Double getrOE() {
        return rOE;
    }

    public Double getEbitda() {
        return ebitda;
    }

    public Double getDividaBrutaPatrimonioLiquido() {
        return dividaBrutaPatrimonioLiquido;
    }

    public Long getNotaGovernaca() {
        return notaGovernaca;
    }

    public LocalDateTime getData() {
        return data;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Builder {
        private Double pL;
        private Double pVPA;
        private Double dY;
        private Double rOE;
        private Double ebitda;
        private Double dividaBrutaPatrimonioLiquido;
        private Long notaGovernaca;
        private LocalDateTime data;

        public Builder() {
            this.pL = null;
            this.pVPA = null;
            this.dY = null;
            this.rOE = null;
            this.ebitda = null;
            this.dividaBrutaPatrimonioLiquido = null;
            this.notaGovernaca = null;
            this.data = LocalDateTime.now();
        }

        public Builder comPL(final Double value ) {
            this.pL = value;
            return this;
        }

        public Builder comPVPA(final Double value ) {
            this.pVPA = value;
            return this;
        }

        public Builder comDY(final Double value ) {
            this.dY = value;
            return this;
        }

        public Builder comROE(final Double value ) {
            this.rOE = value;
            return this;
        }

        public Builder comEbitda(final Double value ) {
            this.ebitda = value;
            return this;
        }

        public Builder comDividaBrutaPatrimonioLiquido(final Double value ) {
            this.dividaBrutaPatrimonioLiquido = value;
            return this;
        }

        public Builder comNotaGovernanca(final Long value ) {
            this.notaGovernaca = value;
            return this;
        }

        public Fundamento build() { return new Fundamento(this);}
    }
}
