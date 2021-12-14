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
        createUser();
    }

    private void createUser(){

        userRepository.deleteAll();

        User user = new User("Joao", "123456", "joao@mail.com", LocalDateTime.now());
        user.setId("1");
        User user2 = new User("Maria", "123456", "maria@mail.com", LocalDateTime.now());
        user2.setId("2");
        User user3 = new User("Joana", "123456", "joana@mail.com", LocalDateTime.now());
        user3.setId("3");
        User user4 = new User("Ze", "123456", "ze@mail.com", LocalDateTime.now());
        user4.setId("4");
        userRepository.saveAll(Arrays.asList(user, user2, user3, user4));
    }

}
