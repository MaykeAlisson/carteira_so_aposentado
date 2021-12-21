package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.constantes.Categoria;
import br.com.carteiradoaposentado.commons.constantes.Setor;
import br.com.carteiradoaposentado.commons.constantes.Tipo;
import br.com.carteiradoaposentado.commons.dto.CarteiraDto;
import br.com.carteiradoaposentado.domain.Carteira;
import br.com.carteiradoaposentado.service.CarteiraService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    @Order(4)
    void criarNovaCarteira() {
        Set<Carteira.PorcentagemTipo> tipos = new HashSet<>();
        tipos.add(new Carteira.PorcentagemTipo(Tipo.ACAO, new BigDecimal("10")));
        Set<Carteira.PorcentagemCategoria> categorias = new HashSet<>();
        categorias.add(new Carteira.PorcentagemCategoria(Categoria.BY_ROAD, new BigDecimal("10")));
        Set<Carteira.PorcentagemSetor> setores = new HashSet<>();
        setores.add(new Carteira.PorcentagemSetor(Setor.FII_FUNDOS, new BigDecimal("10")));
        Set<Carteira.TipoQtds> qtds = new HashSet<>();
        qtds.add(new Carteira.TipoQtds(Tipo.FII, 12L));

        CarteiraDto dto = new CarteiraDto(tipos, categorias, setores, qtds);
        Assertions.assertNotNull(carteiraService.create("4", dto));
    }

    @Test
    @Order(5)
    void criarNovaCarteiraParaUsuarioQueJaTem() {
        Set<Carteira.PorcentagemTipo> tipos = new HashSet<>();
        tipos.add(new Carteira.PorcentagemTipo(Tipo.ACAO, new BigDecimal("10")));
        Set<Carteira.PorcentagemCategoria> categorias = new HashSet<>();
        categorias.add(new Carteira.PorcentagemCategoria(Categoria.BY_ROAD, new BigDecimal("10")));
        Set<Carteira.PorcentagemSetor> setores = new HashSet<>();
        setores.add(new Carteira.PorcentagemSetor(Setor.FII_FUNDOS, new BigDecimal("10")));
        Set<Carteira.TipoQtds> qtds = new HashSet<>();
        qtds.add(new Carteira.TipoQtds(Tipo.FII, 12L));

        final Carteira carteiraAtual = carteiraService.buscarPorIdUsuario("4");
        CarteiraDto dto = new CarteiraDto(tipos, categorias, setores, qtds);
        final Carteira carteira = carteiraService.create("4", dto);
        Assertions.assertEquals(carteiraAtual.getId(), carteira.getId());
    }

}
