package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.UserLoginDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.service.SegurancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/seguranca")
public class SegurancaResource {

    @Autowired
    private SegurancaService segurancaService;

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public ResponseEntity<UsuarioAcessoDto> create(@RequestBody final UserLoginDto dto) {

        // TODO verificar se obj isEmpyt

        return ResponseEntity.status(CREATED).build();
    }
}
