package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;

public interface FundamentoService {

    /**
     * Cria novo registro
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario no banco
     * @param idAtivo id ativo no banco
     * @param dto referencia fundamento
     */
    void insert(final String idUser, final String idAtivo, final FundamentoDto dto);
}
