package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.LancamentoRepository;
import br.com.carteiradoaposentado.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static br.com.carteiradoaposentado.commons.dto.LancamentoDto.fromLancamento;
import static br.com.carteiradoaposentado.commons.dto.LancamentoDto.updateData;
import static java.lang.String.format;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Lancamento create(final String idUser, final LancamentoDto dto) {
        return lancamentoRepository.save(fromLancamento(idUser, dto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Lancamento> findAll(final String idUser) {
        return lancamentoRepository.findAll(idUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lancamento findById(String idUser, String id) {
        return lancamentoRepository.buscarPorId(idUser, id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                format("Lancamento com o id %s não encontrado para este usuario", id)
                        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Lancamento update(final String idUser, final String id, final LancamentoDto dto) {
        return lancamentoRepository.save(updateData(findById(idUser, id), dto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String idUser, final String id) {
        lancamentoRepository.delete(findById(idUser, id));
    }
}
