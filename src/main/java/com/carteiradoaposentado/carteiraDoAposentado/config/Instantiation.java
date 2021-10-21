package com.carteiradoaposentado.carteiraDoAposentado.config;

import com.carteiradoaposentado.carteiraDoAposentado.domain.User;
import com.carteiradoaposentado.carteiraDoAposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user = new User("1", "Mayke", "123456", "mail@g1", LocalDateTime.now());
        User user2 = new User("2", "Maria", "123456", "mail@g1", LocalDateTime.now());
        User user3 = new User("3", "Joana", "123456", "mail@g1", LocalDateTime.now());
        User user4 = new User("4", "Ze", "123456", "mail@g1", LocalDateTime.now());
        userRepository.saveAll(Arrays.asList(user, user2, user3, user4));
    }
}
