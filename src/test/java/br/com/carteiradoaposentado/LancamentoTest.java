package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.constantes.Operacao;
import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.LancamentoRepository;
import br.com.carteiradoaposentado.service.LancamentoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LancamentoTest {

    @Autowired
    private LancamentoService lancamentoService;
    @Autowired
    private LancamentoRepository lancamentoRepository;

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
        Set<Lancamento> all = lancamentoService.findAll("3");
        assertFalse(all.isEmpty());
    }

    @Test
    @Order(3)
    void buscarTodosPorUsuarioInvalido() {
        Set<Lancamento> all = lancamentoService.findAll("25");
        assertTrue(all.isEmpty());
    }

    @Test
    @Order(4)
    void buscarPorId() {
        Lancamento byId = lancamentoService.findById("3", "2");
        assertNotNull(byId);
    }

    @Test
    @Order(5)
    void atualizarLancamento() {
        Lancamento atual = lancamentoService.findById("3", "2");
        LancamentoDto novo = new LancamentoDto("xml", Operacao.VENDA, 7L, LocalDate.now());
        Lancamento update = lancamentoService.update("3", "2", novo);
        assertFalse(
                Objects.equals(atual.getAtivo(), update.getAtivo())
                        && Objects.equals(atual.getOperacao(), update.getOperacao())
                        && Objects.equals(atual.getQtd(), update.getQtd())
                        && Objects.equals(atual.getData(), update.getData())
        );
    }

    @Test
    @Order(6)
    void naoAtualizarLancamento() {
        Lancamento atual = lancamentoService.findById("3", "2");
        LancamentoDto novo = new LancamentoDto("xml", Operacao.VENDA, 7L, LocalDate.now());
        Lancamento update = lancamentoService.update("3", "2", novo);
        assertTrue(
                Objects.equals(atual.getId(), update.getId())
                        && Objects.equals(atual.getIdUser(), update.getIdUser())
        );
    }

    @Test
    @Order(7)
    void delete() {
        lancamentoService.delete("3", "2");
        assertThrows(ResourceNotFoundException.class, () -> lancamentoService.findById("3", "2"));
    }
}
