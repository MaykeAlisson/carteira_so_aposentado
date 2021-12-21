package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;
import br.com.carteiradoaposentado.commons.dto.CarteiraDto;
import br.com.carteiradoaposentado.domain.Carteira;

public interface CarteiraService {

    /**
     * Buscar informações e cria carteira atual
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario banco
     * @return carteira atual
     */
    CarteiraAtualDto buscarAtual(String idUser);

    Carteira create(String idUser, CarteiraDto dto);

    Carteira buscarPorIdUsuario(String idUser);

    Carteira update(
            String idUser,
            String id,
            CarteiraDto dto
    );
}
