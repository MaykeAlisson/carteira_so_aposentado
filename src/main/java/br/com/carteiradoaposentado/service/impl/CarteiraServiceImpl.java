package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Carteira;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

@Service
public class CarteiraServiceImpl implements CarteiraService {

    @Autowired
    private AtivoRepository ativoRepository;

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
                                (map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_DOWN)
                        )
                ).collect(Collectors.toSet());

        final Set<Carteira.PorcentagemCategoria> categorias = Ativo.agruparPorCategoria(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.PorcentagemCategoria(
                                map.getKey(),
                                (map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_DOWN)
                        )
                ).collect(Collectors.toSet());

        final Set<Carteira.PorcentagemSetor> setores = Ativo.agruparPorSetor(ativos)
                .entrySet()
                .stream()
                .map(map ->
                        new Carteira.PorcentagemSetor(
                                map.getKey(),
                                (map.getValue().multiply(new BigDecimal("100").divide(patrimonio, 2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_DOWN)
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
}
