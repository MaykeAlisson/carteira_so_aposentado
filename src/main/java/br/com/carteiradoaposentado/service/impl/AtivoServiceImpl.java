package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.commons.dto.ConstantesValueDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.carteiradoaposentado.commons.dto.AtivoDto.fromAtivo;
import static java.lang.String.format;
import static java.util.Arrays.stream;

@Service
public class AtivoServiceImpl implements AtivoService {

    @Autowired
    private AtivoRepository ativoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Ativo insert(final String idUser, final AtivoDto ativoDto) {
        final Ativo ativo = fromAtivo(idUser, ativoDto);
        if(ativoRepository.buscarPorName(idUser, ativo.getNome()).isPresent()) {
            throw new BussinesException("Já existe ativo com esse nome!");
        }
        return ativoRepository.insert(ativo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ativo findById(final String idUser, final String id) {
        return ativoRepository.buscarPorId(idUser, id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Ativo com o id %s não encontrado para este usuario!", id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Ativo> findAll(final String idUser) {
        return ativoRepository.buscarPorUsuario(idUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final String idUser, final String idAtivo, final AtivoDto dto) {

        final Ativo ativo = AtivoDto.updateData(findById(idUser, idAtivo), dto);
        ativoRepository.save(ativo);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String idUser, final String idAtivo) {
        Ativo ativo = findById(idUser, idAtivo);
        ativoRepository.delete(ativo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ConstantesValueDto> findAllConstantes() {
        final Set<ConstantesValueDto> valores = new HashSet<>();
        valores.add(new ConstantesValueDto.Builder()
                .comNome("tipo")
                .comConstanteValue(
                        stream(Tipo.values())
                                .map(tipo ->
                                        new ConstantesValueDto.ConstanteValue.Builder()
                                                .comValue(tipo.getValor())
                                                .comDescricao(tipo.getDescricao())
                                                .build()).collect(Collectors.toSet())
                )
                .build()
        );
        valores.add(new ConstantesValueDto.Builder()
                .comNome("categoria")
                .comConstanteValue(
                        stream(Categoria.values())
                                .map(categoria ->
                                        new ConstantesValueDto.ConstanteValue.Builder()
                                                .comValue(categoria.getValor())
                                                .comDescricao(categoria.getDescricao())
                                                .build()).collect(Collectors.toSet())
                )
                .build()
        );
        valores.add(new ConstantesValueDto.Builder()
                .comNome("setor")
                .comConstanteValue(
                        stream(Setor.values())
                                .map(setor ->
                                        new ConstantesValueDto.ConstanteValue.Builder()
                                                .comValue(setor.getValor())
                                                .comDescricao(setor.getDescricao())
                                                .build()).collect(Collectors.toSet())
                )
                .build()
        );
        return valores;
    }

}
