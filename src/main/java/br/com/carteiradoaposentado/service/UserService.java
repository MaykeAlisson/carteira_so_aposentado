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
    User insert(final UserCreateDto userCreate);

    User findById(String id);

    void update(String id, UserCreateDto userCreate);

    void delete(String id);
}
