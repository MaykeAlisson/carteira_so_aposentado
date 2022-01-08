package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;

public interface FundamentoService {

    Long LIMITE = 6L;

    /**
     * Cria novo registro
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario no banco
     * @param idAtivo id ativo no banco
     * @param dto referencia fundamento
     */
    void insert(String idUser, String idAtivo, FundamentoDto dto);
}
