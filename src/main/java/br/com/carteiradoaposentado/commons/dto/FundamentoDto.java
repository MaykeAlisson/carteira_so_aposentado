package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.Fundamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FundamentoDto implements Serializable {

    private final Double pL;
    private final Double pVPA;
    private final Double dY;
    private final Double rOE;
    private final Double ebitda;
    private final Double dividaBrutaPatrimonioLiquido;
    private final Long notaGovernanca;

    @JsonCreator
    public FundamentoDto(
            @JsonProperty("pL") final Double pL,
            @JsonProperty("pVPA") final Double pVPA,
            @JsonProperty("dY") final Double dY,
            @JsonProperty("rOE") final Double rOE,
            @JsonProperty("ebitda") final Double ebitda,
            @JsonProperty("dvPL") final Double dividaBrutaPatrimonioLiquido,
            @JsonProperty("notaGov") final Long notaGovernanca
    ) {
        this.pL = pL;
        this.pVPA = pVPA;
        this.dY = dY;
        this.rOE = rOE;
        this.ebitda = ebitda;
        this.dividaBrutaPatrimonioLiquido = dividaBrutaPatrimonioLiquido;
        this.notaGovernanca = notaGovernanca;
    }

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

    public static Fundamento fromFundamento(final FundamentoDto dto){
        return new Fundamento.Builder()
                .comPL(dto.getpL())
                .comPVPA(dto.getpVPA())
                .comDY(dto.getdY())
                .comROE(dto.getrOE())
                .comEbitda(dto.getEbitda())
                .comDividaBrutaPatrimonioLiquido(dto.getDividaBrutaPatrimonioLiquido())
                .comNotaGovernanca(dto.getNotaGovernanca())
                .build();
    }
}