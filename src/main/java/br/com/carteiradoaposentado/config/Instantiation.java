package br.com.carteiradoaposentado.config;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.repository.UserRepository;
import br.com.carteiradoaposentado.service.AtivoService;
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
    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private AtivoService ativoService;

    @Override
    public void run(String... args){
        deleteAll();
        createUser();
        createAtivo();
    }

    private void createUser(){

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

    private void createAtivo(){

        final AtivoDto ativo = new AtivoDto("minusculo", Tipo.ACAO, Categoria.BY_ROAD,
                Setor.CONSUMO, 10L, 35.40, 5D, "");
        ativoService.insert("1", ativo);
    }

    private void deleteAll(){
        ativoRepository.deleteAll();
        userRepository.deleteAll();
    }

}
