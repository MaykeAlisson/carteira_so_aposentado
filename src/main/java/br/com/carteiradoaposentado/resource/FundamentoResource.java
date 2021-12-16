package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.FundamentoDto;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.FundamentoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/fundamentos")
public class FundamentoResource {

    @Autowired
    private FundamentoService fundamentoService;

    @RequestMapping(value = "/v1/{idAtivo}/fundamento", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@PathVariable final String idAtivo, @RequestBody @Valid final FundamentoDto dto) {

        if (ObjectUtils.isEmpty(idAtivo)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        fundamentoService.insert(userId, idAtivo, dto);
        return ResponseEntity.status(CREATED).build();
    }
}
