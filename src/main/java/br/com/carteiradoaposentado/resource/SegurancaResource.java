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

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/seguranca")
public class SegurancaResource {

    @Autowired
    private SegurancaService segurancaService;

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public ResponseEntity<UsuarioAcessoDto> create(@RequestBody @Valid final UserLoginDto dto) {
        return ResponseEntity.ok().body(segurancaService.verificaAcesso(dto));
    }
}
