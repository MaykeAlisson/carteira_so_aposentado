package br.com.carteiradoaposentado.config;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Operacao;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.repository.LancamentoRepository;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public void run(String... args) {
        deleteAll();
        createUser();
        createAtivo();
        createLancamento();
    }
    private void deleteAll() {
        ativoRepository.deleteAll();
        userRepository.deleteAll();
        lancamentoRepository.deleteAll();
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

    private void createLancamento() {
        Lancamento lancamento = new Lancamento("3", "bcff11", new BigDecimal("5.48"), 5L, Operacao.COMPRA, LocalDate.now());
        Lancamento lancamento1 = new Lancamento("3", "via3", new BigDecimal("7.58"), 3L, Operacao.VENDA, LocalDate.now());
        Lancamento lancamento2 = new Lancamento("3", "itsa3", new BigDecimal("10.0"), 10L, Operacao.COMPRA, LocalDate.now());
        Lancamento lancamento3 = new Lancamento("3", "hglg11", new BigDecimal("5.00"), 2L, Operacao.COMPRA, LocalDate.now());
        lancamento3.setId("2");
        lancamentoRepository.saveAll(Arrays.asList(lancamento, lancamento1, lancamento2, lancamento3));
    }

}
