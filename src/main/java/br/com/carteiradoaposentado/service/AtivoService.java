package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.commons.dto.ConstantesValueDto;
import br.com.carteiradoaposentado.domain.Ativo;

import java.util.Set;

public interface AtivoService {

    /**
     * Cria novo ativo
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario no banco
     * @param ativoDto representacao ativo
     * @return Ativo criado
     */
    Ativo insert(final String idUser, final AtivoDto ativoDto);

    /**
     * Buscar ativo por id
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario banco
     * @param id id ativo banco
     * @return Possivel Ativo
     */
    Ativo findById(final String idUser, final String id);

    /**
     * Busca todos ativo do usuario
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id no banc
     * @return Lista de ativos
     */
    Set<Ativo> findAll(final String idUser);

    /**
     * Atualiza ativo
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario banco
     * @param idAtivo id ativo banco
     * @param dto representacao ativo
     */
    void update(final String idUser, final String idAtivo, final AtivoDto dto);

    /**
     * Deleta registro
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario banco
     * @param idAtivo is ativo banco
     */
    void delete(final String idUser, final String idAtivo);

    /**
     * Buscar Constante referente a ativo
     *
     * <p>Autor: Mayke</p>
     *
     * @return Lista de constantes referente ao ativo
     */
    Set<ConstantesValueDto> findAllConstantes();
}
