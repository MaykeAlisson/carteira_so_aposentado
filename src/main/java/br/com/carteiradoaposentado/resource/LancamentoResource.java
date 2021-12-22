package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.LancamentoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/lancamento")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @RequestMapping(value = "/v1/lancamento", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final LancamentoDto dto) {
        final String userId = Token.getUserId();
        lancamentoService.create(userId, dto);
    }

    @RequestMapping(value = "/v1/lancamento", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        final String userId = Token.getUserId();
        lancamentoService.findAll(userId);
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lancamento> findById(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        lancamentoService.findById(userId, id);
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable final String id, @RequestBody @Valid final LancamentoDto dto) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        lancamentoService.update(userId, id, dto);
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        lancamentoService.delete(userId, id);
    }
}
