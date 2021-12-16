package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.Fundamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FundamentoDto implements Serializable {

    @NotNull
    private final Double pL;
    @NotNull
    private final Double pVPA;
    @NotNull
    private final Double dY;
    @NotNull
    private final Double rOE;
    @NotNull
    private final Double ebitda;
    @NotNull(message = "dvPL no null")
    private final Double dividaBrutaPatrimonioLiquido;
    @NotNull(message = "notaGov no null")
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

    public static Fundamento fromFundamento(final FundamentoDto dto) {
        return new Fundamento.Builder()
                .comPL((dto.getpL() < 0) ? 0 : dto.getpL())
                .comPVPA(dto.getpVPA())
                .comDY((dto.getdY() < 0) ? 0 : dto.getdY())
                .comROE(dto.getrOE())
                .comEbitda((dto.getEbitda() < 0) ? 0 : dto.getEbitda())
                .comDividaBrutaPatrimonioLiquido(dto.getDividaBrutaPatrimonioLiquido())
                .comNotaGovernanca((dto.getNotaGovernanca() < 0) ? 0 : dto.getNotaGovernanca())
                .build();
    }
}
