package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Set;

import static br.com.carteiradoaposentado.commons.dto.AtivoDto.fromAtivo;
import static java.lang.String.format;

@Service
public class AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    public Ativo insert(final String idUser, final AtivoDto ativoDto){
        return ativoRepository.insert(fromAtivo(idUser, ativoDto));
    }

    public Ativo findById(final String id){
        // todo receber idUser para buscar oque pertence ao usuario
        return ativoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Ativo com o id %s não encontrado!", id)));
    }

    public Set<Ativo> findAll(final String idUser){
        return ativoRepository.buscarPorUsuario("5");
    }

    // update

    // delete

    // add fundamento
}
