package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.domain.Ativo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JsonCreator
    public AtivoDto(
            @JsonProperty("nome") final String nome,
            @JsonProperty("tipo") final Tipo tipo,
            @JsonProperty("categoria") final Categoria categoria,
            @JsonProperty("setor") final Setor setor,
            @JsonProperty("qtd") final Long qtd,
            @JsonProperty("valor") final Double valor
    ) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.setor = setor;
        this.qtd = qtd;
        this.valor = valor;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Setor getSetor() {
        return setor;
    }

    public Long getQtd() {
        return qtd;
    }

    public Double getValor() {
        return valor;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // MÉTODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Ativo fromAtivo(final String idUser, final AtivoDto dto){
       return new Ativo(dto.getNome(), idUser, dto.getTipo(), dto.getCategoria(), dto.getSetor(), dto.getQtd(), dto.getValor(), LocalDateTime.now(), new HashSet<>());
    }

    public static Ativo updateData(Ativo ativo, final AtivoDto dto){

        ativo.setCategoria(dto.getCategoria());
        ativo.setQtd(dto.getQtd());
        ativo.setValor(dto.getValor());
        return ativo;
    }


}
