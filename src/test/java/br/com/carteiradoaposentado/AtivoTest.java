package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.service.impl.AtivoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AtivoTest {

    @Autowired
    private AtivoServiceImpl ativoService;

    @Test
    void cadastrarAtivo() {
//        ativoService.insert()
    }

    @Test
    void cadastrarAtivoDuplicado() {
        // falhar
    }

    @Test
    void buscarPorId() {

    }

    @Test
    void buscarTodosDoUsuario() {

    }

    @Test
    void atualizar() {
        //
    }

    @Test
    void deletar() {
        //
    }

    @Test
    void deletarDeOutroUsuario() {
        // falhar
    }
}
