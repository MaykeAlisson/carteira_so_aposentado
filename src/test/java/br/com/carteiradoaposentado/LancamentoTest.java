package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.service.LancamentoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LancamentoTest {

    @Autowired
    private LancamentoService lancamentoService;

    @Test
    @Order(1)
    void cadastro() {
        LancamentoDto bcff11 = new LancamentoDto("bcff11", Operacao.COMPRA, 4L, LocalDate.now());
        Lancamento lancamento = lancamentoService.create("1", bcff11);
        assertNotNull(lancamento);
    }

    @Test
    @Order(2)
    void buscarTodosPorUsuario() {
        LancamentoDto bcff11 = new LancamentoDto("bcff11", Operacao.COMPRA, 4L, LocalDate.now());
        Lancamento lancamento = lancamentoService.create("1", bcff11);
        assertNotNull(lancamento);
    }
}
