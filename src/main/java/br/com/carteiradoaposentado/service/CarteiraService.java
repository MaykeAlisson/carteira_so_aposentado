package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;

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
}
