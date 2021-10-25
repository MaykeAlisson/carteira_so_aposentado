package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.carteiradoaposentado.commons.dto.UserCreateDto.fromUser;
import static br.com.carteiradoaposentado.commons.dto.UserCreateDto.updateData;
import static java.lang.String.format;

@Service
public class UserService {

    // TODO criar interface

    @Autowired
    private UserRepository userRepository;

    public User insert(final UserCreateDto userCreate){

        return userRepository.insert(fromUser(userCreate));
    }

    public User findById(final String id){

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Usuario com o id %s não encontrado!", id)));
    }

    public void update(final String id, final UserCreateDto userCreate){

        final User userNew = updateData(findById(id), userCreate);
        userRepository.save(userNew);
    }

    public void delete(final String id){
        final User user = findById(id);
        userRepository.delete(user);
    }

}
