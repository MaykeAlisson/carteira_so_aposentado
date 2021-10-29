package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.Fundamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FundamentoDto implements Serializable {

    @NotNull(message = "pL obrigatorio!") @NotEmpty(message = "pL obrigatorio!")
    private final Double pL;
    @NotNull(message = "pVPA obrigatorio!") @NotEmpty(message = "pVPA obrigatorio!")
    private final Double pVPA;
    @NotNull(message = "dY obrigatorio!") @NotEmpty(message = "dY obrigatorio!")
    private final Double dY;
    @NotNull(message = "rOE obrigatorio!") @NotEmpty(message = "rOE obrigatorio!")
    private final Double rOE;
    @NotNull(message = "ebitda obrigatorio!") @NotEmpty(message = "ebitda obrigatorio!")
    private final Double ebitda;
    @NotNull(message = "dividaBrutaPatrimonioLiquido obrigatorio!") @NotEmpty(message = "dividaBrutaPatrimonioLiquido obrigatorio!")
    private final Double dividaBrutaPatrimonioLiquido;
    @NotNull(message = "notaGovernanca obrigatorio!") @NotEmpty(message = "notaGovernanca obrigatorio!")
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
