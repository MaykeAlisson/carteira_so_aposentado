package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.CarteiraAtualDto;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/carteira")
public class CarteiraResource {

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.GET)
    public ResponseEntity<?> buscarConfig(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.POST)
    public ResponseEntity<?> createConfig(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira", method = RequestMethod.PUT)
    public ResponseEntity<?> updateConfig(){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/carteira/atual", method = RequestMethod.GET)
    public ResponseEntity<CarteiraAtualDto> buscarAtual() {
        final String userId = Token.getUserId();

        return ResponseEntity.ok().build();
    }
}
