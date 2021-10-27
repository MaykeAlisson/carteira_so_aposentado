package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Fundamento;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.carteiradoaposentado.commons.dto.FundamentoDto.fromFundamento;
import static java.lang.String.format;

@Service
public class FundamentoService {

    @Autowired
    private AtivoRepository ativoRepository;

    public void insert(final String idUser, final String idAtivo, final FundamentoDto dto){
        Ativo ativo = ativoRepository.buscarPorId(idUser, idAtivo)
                .orElseThrow(() -> new ResourceNotFoundException(format("Não foi encontrado ativo com o id %s para este usuario!", idAtivo)));

        // garantir somente os ultimos 6 meses
        // nao salvar dos registros para o mesmo mes
        ativo.getAnalise().add(fromFundamento(dto));

        ativoRepository.save(ativo);
    }

    private Set<Fundamento> filtraSeisMeses(Set<Fundamento> fundamentos){

        if (fundamentos.size() < 6) return fundamentos;

        return fundamentos.stream()
                .filter(itens -> itens.getMes() > LocalDateTime.now().minusMonths(6).getMonthValue()).collect(Collectors.toSet());

    }
}
