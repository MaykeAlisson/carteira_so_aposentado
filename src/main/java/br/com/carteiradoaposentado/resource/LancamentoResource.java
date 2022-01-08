package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.LancamentoDto;
import br.com.carteiradoaposentado.domain.Lancamento;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.LancamentoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@RestController
@RequestMapping(value = "/api/lancamento")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @RequestMapping(value = "/v1/lancamento", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final LancamentoDto dto) {
        final String userId = Token.getUserId();
        final Lancamento lancamento = lancamentoService.create(userId, dto);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/v1/lancamento", method = RequestMethod.GET)
    public ResponseEntity<Set<Lancamento>> findAll() {
        final String userId = Token.getUserId();
        final Set<Lancamento> lancamentos = lancamentoService.findAll(userId);
        return isEmpty(lancamentos) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(lancamentos);
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lancamento> findById(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        return ResponseEntity.ok().body(lancamentoService.findById(userId, id));
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody @Valid final LancamentoDto dto) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        lancamentoService.update(userId, id, dto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/lancamento/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        lancamentoService.delete(userId, id);
        return ResponseEntity.ok().build();
    }
}
