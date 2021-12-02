package br.com.carteiradoaposentado.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ConstantesValueDto implements Serializable {

    private final String nome;
    private final Set<ConstanteValue> constanteValue;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ConstantesValueDto(final Builder builder){
        this.nome = builder.nome;
        this.constanteValue = builder.constanteValue;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("constanteValue")
    public Set<ConstanteValue> getConstanteValue() {
        return constanteValue;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BUILDER
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Builder {
        private String nome;
        private Set<ConstanteValue> constanteValue;

        public Builder(  ) {
            this.nome = null;
            this.constanteValue = null;
        }

        public Builder comNome(final String value){
            this.nome = value;
            return this;
        }

        public Builder comConstanteValue(final Set<ConstanteValue> value){
            this.constanteValue = value;
            return this;
        }

        public ConstantesValueDto build(){ return new ConstantesValueDto(this); }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EQUALS && HASHCODE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstantesValueDto that = (ConstantesValueDto) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public static class ConstanteValue{
        private final Short value;
        private final String descricao;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // CONSTRUCTOR
        //
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public ConstanteValue(final Builder builder){
            this.value = builder.value;
            this.descricao = builder.descricao;
        }

        @JsonProperty("value")
        public Short getValue() {
            return value;
        }

        @JsonProperty("descricao")
        public String getDescricao() {
            return descricao;
        }

        public static class Builder {
            private Short value;
            private String descricao;

            public Builder(){
                this.value = null;
                this.descricao = null;
            }

            public Builder comValue(final Short value){
                this.value = value;
                return this;
            }

            public Builder comDescricao(final String value){
                this.descricao = value;
                return this;
            }

            public ConstanteValue build(){return new ConstanteValue(this);}
        }
    }
}
