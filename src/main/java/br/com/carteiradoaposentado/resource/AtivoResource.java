package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.commons.dto.ConstantesValueDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.AtivoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@RestController
@RequestMapping(value = "/api/ativos")
public class AtivoResource {

    @Autowired
    private AtivoService ativoService;

    @RequestMapping(value = "/v1/ativo", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid final AtivoDto dto) {

        final String userId = Token.getUserId();
        final Ativo ativo = ativoService.insert(userId, dto);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ativo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ativo> findById(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        final Ativo ativo = ativoService.findById(userId, id);
        return ResponseEntity.ok().body(ativo);
    }

    @RequestMapping(value = "/v1/ativo", method = RequestMethod.GET)
    public ResponseEntity<Set<Ativo>> findAll() {
        final String userId = Token.getUserId();
        final Set<Ativo> ativos = ativoService.findAll(userId);
        return isEmpty(ativos) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(ativos);
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody @Valid final AtivoDto dto) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        ativoService.update(userId, id, dto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }
        final String userId = Token.getUserId();
        ativoService.delete(userId, id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/constantes", method = RequestMethod.GET)
    public ResponseEntity<Set<ConstantesValueDto>> findAllConstantes() {
        return ResponseEntity.ok().body(ativoService.findAllConstantes());
    }
}
