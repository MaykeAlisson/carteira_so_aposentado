package com.carteiradoaposentado.carteiraDoAposentado.service;

import com.carteiradoaposentado.carteiraDoAposentado.domain.User;
import com.carteiradoaposentado.carteiraDoAposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){

        return userRepository.findAll();
    }

    public Optional<User> findById(final String id){

        return userRepository.findById(id);
    }
}
