package br.com.carteiradoaposentado.commons.dto;

import br.com.carteiradoaposentado.domain.Carteira;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CarteiraDto implements Serializable {

    private static final long serialVersionUID = 1755880811343150739L;

    @NotNull
    private final Set<Carteira.PorcentagemTipo> porcentagemTipo;
    @NotNull
    private final Set<Carteira.PorcentagemCategoria> porcentagemCategoria;
    @NotNull
    private final Set<Carteira.TipoQtds> tipoQtds;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONSTRUCTOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JsonCreator
    public CarteiraDto(
            @JsonProperty("porcentagemTipo") Set<Carteira.PorcentagemTipo> porcentagemTipo,
            @JsonProperty("porcentagemCategoria") Set<Carteira.PorcentagemCategoria> porcentagemCategoria,
            @JsonProperty("tipoQtds") Set<Carteira.TipoQtds> tipoQtds
    ) {
        this.porcentagemTipo = porcentagemTipo;
        this.porcentagemCategoria = porcentagemCategoria;
        this.tipoQtds = tipoQtds;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Set<Carteira.PorcentagemTipo> getPorcentagemTipo() {
        return porcentagemTipo;
    }

    public Set<Carteira.PorcentagemCategoria> getPorcentagemCategoria() {
        return porcentagemCategoria;
    }

    public Set<Carteira.TipoQtds> getTipoQtds() {
        return tipoQtds;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // M??TODOS AUXILIARES
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Carteira fromCateira(final String idUser, final CarteiraDto dto) {
        return new Carteira(idUser, dto.getPorcentagemTipo(), dto.getPorcentagemCategoria(),
                new HashSet<>(), dto.getTipoQtds()
                );
    }

    public static Carteira fromUpdate(Carteira carteira, final CarteiraDto dto) {
        carteira.setPorcentagemCategoria(dto.getPorcentagemCategoria());
        carteira.setPorcentagemTipo(dto.getPorcentagemTipo());
        carteira.setTipoQtds(dto.getTipoQtds());
        return carteira;
    }
}
