package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.commons.dto.ConstantesValueDto;
import br.com.carteiradoaposentado.domain.Ativo;

import java.util.Set;

public interface AtivoService {
    Ativo insert(String idUser, AtivoDto ativoDto);

    Ativo findById(String idUser, String id);

    Set<Ativo> findAll(String idUser);

    void update(String idUser, String idAtivo, AtivoDto dto);

    void delete(String idUser, String idAtivo);

    Set<ConstantesValueDto> findAllConstantes();
}
