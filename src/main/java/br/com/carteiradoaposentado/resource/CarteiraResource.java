package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;
import br.com.carteiradoaposentado.commons.dto.CarteiraDto;
import br.com.carteiradoaposentado.domain.Carteira;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.CarteiraService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/carteira")
public class CarteiraResource {

    @Autowired
    private CarteiraService carteiraService;

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.GET)
    public ResponseEntity<?> buscarConfig(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.POST)
    public ResponseEntity<Carteira> createConfig(@RequestBody @Valid final CarteiraDto model){
        /*
        {
    "porcentagemTipo": [
        {
         "tipo": 3,
         "porcentagem": 20
        },
        {
         "tipo": 1,
         "porcentagem": 20
        },
        {
         "tipo": 2,
         "porcentagem": 20
        },
        {
         "tipo": 4,
         "porcentagem": 20
        },
        {
         "tipo": 8,
         "porcentagem": 5
        },
         {
         "tipo": 6,
         "porcentagem": 5
        },
         {
         "tipo": 7,
         "porcentagem": 10
        }
    ],
    "porcentagemCategoria": [
         {
         "categoria": 1,
         "porcentagem": 10
        },
        {
         "categoria": 2,
         "porcentagem": 80
        },
        {
         "categoria": 3,
         "porcentagem": 10
        }
    ],
    "porcentagemSetor": [],
    "tipoQtds": [
         {
         "tipo": 3,
         "qtd": 15
        },
        {
         "tipo": 1,
         "qtd": 11
        },
        {
         "tipo": 2,
         "qtd": 15
        },
        {
         "tipo": 4,
         "qtd": 5
        },
        {
         "tipo": 8,
         "qtd": 3
        },
         {
         "tipo": 6,
         "qtd": 6
        },
         {
         "tipo": 7,
         "qtd": 5
        }
    ]
}
         */
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.PUT)
    public ResponseEntity<?> updateConfig(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira/atual", method = RequestMethod.GET)
    public ResponseEntity<CarteiraAtualDto> buscarAtual() {
        final String userId = Token.getUserId();
        return ResponseEntity.ok().body(carteiraService.buscarAtual(userId));
    }
}
