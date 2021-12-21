package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.service.CarteiraService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarteiraTest {

    @Autowired
    private CarteiraService carteiraService;

    @Test
    @Order(1)
    void buscarCarteiraAtualParaClienteComAtivo() {
        Assertions.assertNotNull(carteiraService.buscarAtual("4").getPatrimonio());
    }

    @Test
    @Order(2)
    void buscarCarteiraAtualParaClienteSemAtivo() {
        Assertions.assertEquals(carteiraService.buscarAtual("3").getPatrimonio(), new BigDecimal("0.00"));
    }

    @Test
    @Order(3)
    void buscarCarteiraAtualParaClienteQueNaoExiste() {
        Assertions.assertEquals(carteiraService.buscarAtual("58").getPatrimonio(), new BigDecimal("0.00"));
    }

    @Test
    void criarNovaCarteira() {

    }

    @Test
    void criarNovaCarteiraParaUsuarioQueJaTem() {

    }

    @Test
    void atualizarCarteira() {

    }
    
}
