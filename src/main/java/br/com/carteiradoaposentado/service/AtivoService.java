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

    public Ativo findById(final String idUser , final String id){
        return ativoRepository.buscarPorId(idUser, id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Ativo com o id %s não encontrado para este usuario!", id)));
    }

    public Set<Ativo> findAll(final String idUser){
        return ativoRepository.buscarPorUsuario(idUser);
    }

    public void update(final String idUser, final String idAtivo, final AtivoDto dto){

        final Ativo ativo = AtivoDto.updateData(findById(idUser, idAtivo), dto);
        ativoRepository.save(ativo);

    }

    public void delete(final String idUser, final String idAtivo){
        Ativo ativo = findById(idUser, idAtivo);
        ativoRepository.delete(ativo);
    }

}
