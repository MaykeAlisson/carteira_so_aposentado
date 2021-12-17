package br.com.carteiradoaposentado.config;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Profile("test")
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public void run(String... args) {
        deleteAll();
        createUser();
        createAtivo();
    }

    private void createUser() {

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

    private void createAtivo() {

        final Ativo ativo = new Ativo("Ativo", "1", Tipo.ACAO, Categoria.BY_ROAD, Setor.CONSUMO, 10L, new BigDecimal("35.40"),
                5L, "", LocalDateTime.now(), new HashSet<>());
        ativo.setId("1");
        ativo.setIdUser("1");
        final Ativo ativo2 = new Ativo("Ativo2", "1", Tipo.FII, Categoria.BY_ROAD, Setor.FII_FUNDOS, 10L, new BigDecimal("5.40"),
                5L, "", LocalDateTime.now(), new HashSet<>());
        ativo2.setId("2");
        ativo2.setIdUser("1");
        final Ativo ativo3 = new Ativo("Ativo3", "1", Tipo.ETF, Categoria.BY_ROAD, Setor.FINANCEIRO, 10L, new BigDecimal("5.40"),
                5L, "", LocalDateTime.now(), new HashSet<>());
        ativo3.setId("3");
        ativo3.setIdUser("4");
        final Ativo ativo4 = new Ativo("Ativo4", "1", Tipo.CRIPTO, Categoria.TPB, Setor.OUTROS, 10L, new BigDecimal("5.40"),
                5L, "", LocalDateTime.now(), new HashSet<>());
        ativo4.setId("4");
        ativo4.setIdUser("4");
        ativoRepository.save(ativo);
        ativoRepository.save(ativo2);
        ativoRepository.save(ativo3);
        ativoRepository.save(ativo4);
    }

    private void deleteAll() {
        ativoRepository.deleteAll();
        userRepository.deleteAll();
    }

}
