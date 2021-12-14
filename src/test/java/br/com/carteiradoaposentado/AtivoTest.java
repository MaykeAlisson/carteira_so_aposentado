package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.service.AtivoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class AtivoTest {

    @Autowired
    private AtivoService ativoService;

    @Test
    void cadastrarAtivo() {
        final AtivoDto ativo = new AtivoDto("minusculo", Tipo.ACAO, Categoria.BY_ROAD,
                Setor.CONSUMO, 10L, 35.40, 5D, "");
        final Ativo insert = ativoService.insert("1", ativo);
        Assertions.assertNotNull(insert);
    }

    @Test
    void buscarTodosDoUsuario() {
        final Set<Ativo> all = ativoService.findAll("1");
        Assertions.assertNotNull(all);
    }

    @Test
    void buscarTodosUsuarioNaoExistente() {
        final Set<Ativo> all = ativoService.findAll("20");
        Assertions.assertNull(all);
    }

    @Test
    void atualizar() {
        //
    }

}
