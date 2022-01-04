package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;
import br.com.carteiradoaposentado.commons.dto.CarteiraDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Carteira;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.repository.CarteiraRepository;
import br.com.carteiradoaposentado.service.CarteiraService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.carteiradoaposentado.commons.dto.CarteiraDto.fromCateira;
import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

@Service
public class CarteiraServiceImpl implements CarteiraService {

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CarteiraAtualDto buscarAtual(final String idUser) {

        final Set<Ativo> ativos = ativoRepository.buscarPorUsuario(idUser);

        final BigDecimal patrimonio = ativos.stream().map(Ativo::getValorTotal)
                .reduce(ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

        final Set<Carteira.PorcentagemTipo> tipos = Ativo.agruparPorTipo(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.PorcentagemTipo(
                                map.getKey(),
                                map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_DOWN)
                        )
                ).collect(Collectors.toSet());

        final Set<Carteira.PorcentagemCategoria> categorias = Ativo.agruparPorCategoria(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.PorcentagemCategoria(
                                map.getKey(),
                                map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_DOWN)
                        )
                ).collect(Collectors.toSet());

        final Set<Carteira.PorcentagemSetor> setores = Ativo.agruparPorSetor(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.PorcentagemSetor(
                                map.getKey(),
                                map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_DOWN)
                        )
                ).collect(Collectors.toSet());

        final Set<Carteira.TipoQtds> tipoQtds = Ativo.agruparTipoQtd(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.TipoQtds(
                                map.getKey(),
                                map.getValue())
                ).collect(Collectors.toSet());

        final Map<String, BigDecimal> nomeValor = Ativo.agruparNomeAtivoValor(ativos);

        return new CarteiraAtualDto.Builder()
                .comPorcentagemCategoria(categorias)
                .comPorcentagemTipo(tipos)
                .comPorcentagemSetor(setores)
                .comTipoQtds(tipoQtds)
                .comValorPorAtivo(nomeValor)
                .comPatrimonio(patrimonio)
                .build();
    }

    @Override
    public Carteira create(final String idUser, final CarteiraDto dto) {
        final Carteira carteira = carteiraRepository.buscarPorIdUsuario(idUser);
        if (ObjectUtils.isEmpty(carteira)) {
            return carteiraRepository.save(fromCateira(idUser, dto));
        }
        return update(idUser, carteira.getId(), dto);
    }

    @Override
    public Carteira buscarPorIdUsuario(final String idUser) {
        return carteiraRepository.buscarPorIdUsuario(idUser);
    }

    @Override
    public Carteira update(
            final String idUser,
            final String id,
            final CarteiraDto dto
            ) {
        Carteira carteira = carteiraRepository.buscarPorIdEUsuario(idUser, id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                format("Não foi encontrado carteira com o id %s para este usuario", id)));

        return carteiraRepository.save(CarteiraDto.fromUpdate(carteira, dto));
    }
}
