package br.com.carteiradoaposentado.domain;

import java.io.Serializable;

import static java.time.LocalDateTime.now;

public class Fundamento implements Serializable {

    //https://blog.genialinvestimentos.com.br/indicadores-fundamentalistas/

    private Double pL;
    private Double pVPA;
    private Double dY;
    private Double rOE;
    private Double ebitda;
    private Double dividaBrutaPatrimonioLiquido;
    private Long notaGovernanca;
    private Integer mes;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Deprecated
    public Fundamento(){}

    @Deprecated
    public Fundamento(
            final Double pL,
            final Double pVPA,
            final Double dY,
            final Double rOE,
            final Double ebitda,
            final Double dividaBrutaPatrimonioLiquido,
            final Long notaGovernanca,
            final Integer mes
    ) {
        this.pL = pL;
        this.pVPA = pVPA;
        this.dY = dY;
        this.rOE = rOE;
        this.ebitda = ebitda;
        this.dividaBrutaPatrimonioLiquido = dividaBrutaPatrimonioLiquido;
        this.notaGovernanca = notaGovernanca;
        this.mes = mes;
    }

    public Fundamento(Builder builder){
        this.pL = builder.pL;
        this.pVPA = builder.pVPA;
        this.dY = builder.dY;
        this.rOE = builder.rOE;
        this.ebitda = builder.ebitda;
        this.dividaBrutaPatrimonioLiquido = builder.dividaBrutaPatrimonioLiquido;
        this.notaGovernanca = builder.notaGovernanca;
        this.mes = builder.mes;
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

    public Long getNotaGovernanca() {
        return notaGovernanca;
    }

    public Integer getMes() {
        return mes;
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
        private Long notaGovernanca;
        private final Integer mes;

        public Builder() {
            this.pL = null;
            this.pVPA = null;
            this.dY = null;
            this.rOE = null;
            this.ebitda = null;
            this.dividaBrutaPatrimonioLiquido = null;
            this.notaGovernanca = null;
            this.mes = now().minusMonths(8).getMonthValue();
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
            this.notaGovernanca = value;
            return this;
        }

        public Fundamento build() { return new Fundamento(this);}
    }
}
