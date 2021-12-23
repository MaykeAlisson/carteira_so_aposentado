package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;

import java.util.Optional;
import java.util.Set;

public interface LancamentoService {

    /**
     * Cria novo registro
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario referente no banco
     * @param dto novas info lancamento
     * @return Lancamento atualizado
     */
    Lancamento create(String idUser, LancamentoDto dto);

    /**
     * busca todos lancamento do usuario
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario referennte no banco de dados
     * @return Retorna lista de reegistros do usuario
     */
    Set<Lancamento> findAll(String idUser);

    /**
     * Busca possivel lancamento do usuario por id
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario referente no banco
     * @param id id lancamento referente no banco
     * @return Possivel Lancamento
     */
    Optional<Lancamento> findById(String idUser, String id);

    /**
     * Atualiza Lancamento
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario refrente no banco de dados
     * @param id id do lancamento no banco de dados
     * @param dto representacao dados para atualizar lancamento
     * @return Lancamento atualizado
     */
    Lancamento update(String idUser, String id, LancamentoDto dto);

    /**
     * Deleta Lancamento
     *
     * <p>Autor: Mayke</p>
     *
     * @param idUser id usuario no banco de dados
     * @param id id do lancamento no banco de dados
     */
    void delete(String idUser, String id);
}
