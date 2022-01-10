package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.service.AtivoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class AtivoTest {

    @Autowired
    private AtivoService ativoService;

    @Test
    @Order(1)
    void cadastrarAtivo() {
        final String nome = String.valueOf(UUID.randomUUID());
        final AtivoDto ativo = new AtivoDto(nome, Tipo.ACAO, Categoria.BY_ROAD,
                Setor.CONSUMO, 10L, new BigDecimal("35.40"), 5L, "");
        final Ativo insert = ativoService.insert("1", ativo);
        Assertions.assertNotNull(insert);
    }

    @Test
    @Order(2)
    void buscarTodosDoUsuario() {
        final Set<Ativo> all = ativoService.findAll("1");
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    @Order(3)
    void buscarTodosUsuarioNaoExistente() {
        final Set<Ativo> all = ativoService.findAll("20");
        Assertions.assertTrue(all.isEmpty());
    }

    @Test
    @Order(4)
    void buscarPorId() {
        Assertions.assertNotNull(ativoService.findById("1", "1"));
    }

    @Test
    @Order(5)
    void buscarPorIdNaoExiste() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> ativoService.findById("1", "50"));
    }

    @Test
    @Order(6)
    void atualizar() {
        final Ativo ativo = ativoService.findById("1", "1");
        final AtivoDto updateAtivo = new AtivoDto("ATIVO", Tipo.ETF, Categoria.TPB,
                Setor.FII_GALPOES, 12L, new BigDecimal("39.40"), 8L, "NOvadescricao");
        ativoService.update("1", ativo.getId(), updateAtivo);
        final Ativo novoAtivo = ativoService.findById("1", "1");
        Assertions.assertFalse(
                Objects.equals(ativo.getCategoria(), novoAtivo.getCategoria())
                        && Objects.equals(ativo.getQtd(), novoAtivo.getQtd())
                        && Objects.equals(ativo.getValor(), novoAtivo.getValor())
                        && Objects.equals(ativo.getPorcentagem(), novoAtivo.getPorcentagem())
                        && Objects.equals(ativo.getObservacao(), novoAtivo.getObservacao())
        );
    }

    @Test
    @Order(7)
    void naoAtualizarCampos() {
        final Ativo ativo = ativoService.findById("1", "1");
        final AtivoDto updateAtivo = new AtivoDto("ATIVO", Tipo.ETF, Categoria.TPB,
                Setor.FII_GALPOES, 12L, new BigDecimal("39.40"), 8L, "NOvadescricao");
        ativoService.update("1", ativo.getId(), updateAtivo);
        final Ativo novoAtivo = ativoService.findById("1", "1");
        Assertions.assertTrue(
                Objects.equals(ativo.getNome(), novoAtivo.getNome())
                        && Objects.equals(ativo.getIdUser(), novoAtivo.getIdUser())
                        && Objects.equals(ativo.getCriacao(), novoAtivo.getCriacao())
        );
    }

    @Test
    @Order(8)
    void deletar() {
        final Ativo ativo = ativoService.findById("1", "1");
        ativoService.delete("1", ativo.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> ativoService.findById("1", ativo.getId()));
    }

}
