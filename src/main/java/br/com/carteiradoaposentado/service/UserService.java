package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;

public interface UserService {

    /**
     * Cria novo usuario
     *
     * <p>Author: Mayke</p>
     *
     * @param userCreate Dto representando cliente
     * @return User criado
     */
    User insert(UserCreateDto userCreate);

    /**
     * Buscar usuario por id
     *
     * <p>Autor: Mayke</p>
     *
     * @param id id registro banco
     * @return possivel User
     */
    User findById(String id);

    /**
     * Atualiza cliente com Nome e Senha
     *
     * <p>Autor: Mayke</p>
     *
     * @param id id registro banco
     * @param userCreate Representacao usuario
     */
    void update(String id, UserCreateDto userCreate);

    /**
     * Deleta Usuario
     *
     * <p>Autor: Mayke</p>
     *
     * @param id id registro banco
     */
    void delete(String id);
}
