package br.com.carteiradoaposentado.config;

import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user = new User("Mayke", "123456", "mail@g1", LocalDateTime.now());
        User user2 = new User( "Maria", "123456", "mail@g1", LocalDateTime.now());
        User user3 = new User( "Joana", "123456", "mail@g1", LocalDateTime.now());
        User user4 = new User( "Ze", "123456", "mail@g1", LocalDateTime.now());
        userRepository.saveAll(Arrays.asList(user, user2, user3, user4));
    }
}
