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
