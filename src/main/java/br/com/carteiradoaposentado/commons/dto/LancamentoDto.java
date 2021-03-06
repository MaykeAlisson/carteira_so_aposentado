package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import br.com.carteiradoaposentado.commons.json.DateDeserializer;
import br.com.carteiradoaposentado.commons.json.DateSerializer;
import br.com.carteiradoaposentado.domain.Lancamento;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class LancamentoDto implements Serializable {

    private static final long serialVersionUID = 59493000154322246L;

    @NotNull
    private final String ativo;
    @NotNull
    private final BigDecimal valor;
    @NotNull
    private final Operacao operacao;
    @NotNull @Min(value = 1)
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
            @JsonProperty("valor") final BigDecimal valor,
            @JsonProperty("operacao") final Operacao operacao,
            @JsonProperty("qtd") final Long qtd,
            @JsonProperty("data") @JsonDeserialize(using = DateDeserializer.class) final LocalDate data
    ) {
        this.ativo = ativo;
        this.valor = valor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public Long getQtd() {
        return qtd;
    }

    @JsonSerialize(using = DateSerializer.class)
    public LocalDate getData() {
        return data;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // M??TODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Lancamento fromLancamento(final String idUser, final LancamentoDto dto) {
        return new Lancamento(idUser, dto.getAtivo().trim().toUpperCase(Locale.ROOT), dto.getValor(), dto.getQtd(), dto.getOperacao(), dto.getData());
    }

    public static Lancamento updateData(Lancamento lancamento, final LancamentoDto dto) {
        lancamento.setAtivo(dto.getAtivo().trim().toUpperCase(Locale.ROOT));
        lancamento.setValor(dto.getValor());
        lancamento.setOperacao(dto.getOperacao());
        lancamento.setQtd(dto.getQtd());
        lancamento.setData(dto.getData());
        return lancamento;
    }
}
