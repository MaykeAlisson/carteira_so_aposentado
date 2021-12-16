package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.UserRepository;
import br.com.carteiradoaposentado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.carteiradoaposentado.commons.dto.UserCreateDto.fromUser;
import static br.com.carteiradoaposentado.commons.dto.UserCreateDto.updateData;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public User insert(final UserCreateDto userCreate){

        final Optional<User> possivelUser = userRepository.buscarPorEmail(userCreate.getEmail());
        if (possivelUser.isPresent()) {
            throw new BussinesException("Email já cadastrado");
        }

        return userRepository.insert(fromUser(userCreate));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findById(final String id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Usuario com o id %s não encontrado!", id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final String id, final UserCreateDto userCreate) {

        final User userNew = updateData(findById(id), userCreate);
        userRepository.save(userNew);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String id) {
        final User user = findById(id);
        userRepository.delete(user);
    }

}
