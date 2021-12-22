package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;

public interface LancamentoService {

    Lancamento create(String idUser, LancamentoDto dto);
}
