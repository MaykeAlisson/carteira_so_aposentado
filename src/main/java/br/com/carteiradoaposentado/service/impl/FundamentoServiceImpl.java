package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Fundamento;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.service.FundamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.carteiradoaposentado.commons.dto.FundamentoDto.fromFundamento;
import static java.lang.String.format;

@Service
public class FundamentoServiceImpl implements FundamentoService {

    @Autowired
    private AtivoRepository ativoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(final String idUser, final String idAtivo, final FundamentoDto dto) {
        Ativo ativo = ativoRepository.buscarPorId(idUser, idAtivo)
                .orElseThrow(() -> new ResourceNotFoundException(format("Não foi encontrado ativo com o id %s para este usuario!", idAtivo)));

        ativo.updateAnalises(fromFundamento(dto));
        Set<Fundamento> fundamentos = filtrarSeisMeses(ativo.getAnalise());
        ativo.setAnalise(fundamentos);
        ativoRepository.save(ativo);
    }

    private Set<Fundamento> filtrarSeisMeses(Set<Fundamento> fundamentos) {

        if (fundamentos.size() < 6) return fundamentos;

        List<Fundamento> listFundamentos = new ArrayList<>(fundamentos);
        listFundamentos.sort(Comparator.comparing(Fundamento::getMes).reversed());
        return listFundamentos.stream().limit(6).collect(Collectors.toSet());
    }
}
