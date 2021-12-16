package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.json.TipoAtivoSerializer;
import br.com.carteiradoaposentado.commons.json.TipoAtivoDeserialize;
import br.com.carteiradoaposentado.commons.json.CategoriaAtivoDeserialize;
import br.com.carteiradoaposentado.commons.json.SetorAtivoDeserialize;
import br.com.carteiradoaposentado.commons.json.CategoriaAtivoSerializer;
import br.com.carteiradoaposentado.commons.json.SetorAtivoSerializer;
import br.com.carteiradoaposentado.domain.Ativo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;

public class AtivoDto implements Serializable {

    @NotNull
    private final String nome;
    @NotNull
    private final Tipo tipo;
    @NotNull
    private final Categoria categoria;
    @NotNull
    private final Setor setor;
    @NotNull @Min(value = 1)
    private final Long qtd;
    @NotNull
    private final Double valor;
    @NotNull @Min(value = 1)
    private final Long porcentagem;
    @NotNull
    private final String observacao;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JsonCreator
    public AtivoDto(
            @JsonProperty("nome") final String nome,
            @JsonProperty("tipo") @JsonDeserialize(using = TipoAtivoDeserialize.class) final Tipo tipo,
            @JsonProperty("categoria") @JsonDeserialize(using = CategoriaAtivoDeserialize.class) final Categoria categoria,
            @JsonProperty("setor") @JsonDeserialize(using = SetorAtivoDeserialize.class) final Setor setor,
            @JsonProperty("qtd") final Long qtd,
            @JsonProperty("valor") final Double valor,
            @JsonProperty("porcentagem") Long porcentagem,
            @JsonProperty("observacao") String observacao
            ) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.setor = setor;
        this.qtd = qtd;
        this.porcentagem = porcentagem;
        this.valor = valor;
        this.observacao = observacao;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getNome() {
        return nome;
    }

    @JsonSerialize(using = TipoAtivoSerializer.class)
    public Tipo getTipo() {
        return tipo;
    }

    @JsonSerialize(using = CategoriaAtivoSerializer.class)
    public Categoria getCategoria() {
        return categoria;
    }

    @JsonSerialize(using = SetorAtivoSerializer.class)
    public Setor getSetor() {
        return setor;
    }

    public Long getQtd() {
        return qtd;
    }

    public Double getValor() {
        return valor;
    }

    public Long getPorcentagem() {
        return porcentagem;
    }

    public String getObservacao() {
        return observacao;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MÉTODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Ativo fromAtivo(final String idUser, final AtivoDto dto) {
       return new Ativo(dto.getNome().trim().toUpperCase(), idUser, dto.getTipo(), dto.getCategoria(), dto.getSetor(),
               dto.getQtd(), dto.getValor(), dto.getPorcentagem(), dto.getObservacao(), LocalDateTime.now(),
               new HashSet<>());
    }

    public static Ativo updateData(Ativo ativo, final AtivoDto dto) {
        ativo.setCategoria(dto.getCategoria());
        ativo.setQtd((dto.getQtd() < 0) ? 0 : dto.getQtd());
        ativo.setValor((dto.getValor() < 0) ? 0 : dto.getValor());
        ativo.setPorcentagem((dto.getPorcentagem() < 0) ? 0 : dto.getPorcentagem());
        ativo.setObservacao(dto.getObservacao());
        return ativo;
    }

}
