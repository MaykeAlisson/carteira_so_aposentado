package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import br.com.carteiradoaposentado.commons.json.DateDeserializer;
import br.com.carteiradoaposentado.commons.json.DateSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class LancamentoDto implements Serializable {

    @NotNull
    private final String ativo;
    @NotNull
    private final Operacao operacao;
    @NotNull
    private final Long qtd;
    @NotNull
    private final LocalDate data;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @JsonCreator
    public LancamentoDto(
            @JsonProperty("ativo") final String ativo,
            @JsonProperty("operacao") final Operacao operacao,
            @JsonProperty("qtd") final Long qtd,
            @JsonProperty("data") @JsonDeserialize(using = DateDeserializer.class) final LocalDate data
    ) {
        this.ativo = ativo;
        this.operacao = operacao;
        this.qtd = qtd;
        this.data = data;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getAtivo() {
        return ativo;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public Long getQtd() {
        return qtd;
    }

    @JsonSerialize( using = DateSerializer.class )
    public LocalDate getData() {
        return data;
    }
}
