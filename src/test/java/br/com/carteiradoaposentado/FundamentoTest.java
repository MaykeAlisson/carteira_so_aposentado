package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.domain.Fundamento;
import br.com.carteiradoaposentado.repository.AtivoRepository;
import br.com.carteiradoaposentado.service.AtivoService;
import br.com.carteiradoaposentado.service.FundamentoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FundamentoTest {

    @Autowired
    private FundamentoService fundamentoService;

    @Autowired
    private AtivoRepository ativoRepository;

    @Test
    @Order(1)
    void cadastro() {
        FundamentoDto fundamentoDto = new FundamentoDto(10D, 5D, 0D, 20D, 30001D, -154247D, 6L);
        fundamentoService.insert("1", "2", fundamentoDto);
        Optional<Set<Fundamento>> fundamentos = ativoRepository.buscarPorId("1", "2").map(Ativo::getAnalise);
        Assertions.assertFalse(fundamentos.get().isEmpty());
    }
}
