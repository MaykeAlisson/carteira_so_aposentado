package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;

public interface FundamentoService {
    void insert(String idUser, String idAtivo, FundamentoDto dto);
}
