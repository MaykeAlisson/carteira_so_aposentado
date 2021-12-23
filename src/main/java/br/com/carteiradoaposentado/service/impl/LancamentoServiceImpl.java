package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.service.LancamentoService;

import java.util.Optional;
import java.util.Set;

public class LancamentoServiceImpl implements LancamentoService {
    
    @Override
    public Lancamento create(String idUser, LancamentoDto dto) {
        return null;
    }

    @Override
    public Set<Lancamento> findAll(String idUser) {
        return null;
    }

    @Override
    public Optional<Lancamento> findById(String idUser, String id) {
        return Optional.empty();
    }

    @Override
    public Lancamento update(String idUser, String id, LancamentoDto dto) {
        return null;
    }

    @Override
    public void delete(String idUser, String id) {

    }
}
