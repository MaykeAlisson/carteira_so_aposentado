package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.AtivoDto;
import br.com.carteiradoaposentado.domain.Ativo;
import br.com.carteiradoaposentado.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@RestController
@RequestMapping(value = "/api/ativos")
public class AtivoResource {

    @Autowired
    private AtivoService ativoService;

    @RequestMapping(value = "/v1/ativo", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final AtivoDto dto) {

        // TODO verificar se obj isEmpyt
        // pegar no token o idUser

        final Ativo ativo = ativoService.insert("1", dto);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ativo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ativo> findById(@PathVariable final String id) {
        // todo verificar se id isNotEmpyt
        // pegar no token o idUser
        final Ativo ativo = ativoService.findById("5", id);
        return ResponseEntity.ok().body(ativo);
    }

    @RequestMapping(value = "/v1/ativo", method = RequestMethod.GET)
    public ResponseEntity<Set<Ativo>> findAll() {
        // capturar idUser
        final Set<Ativo> ativos = ativoService.findAll("1");
        return isEmpty(ativos) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(ativos);
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody final AtivoDto dto) {
        // todo verificar se id e dto isNotEmpyt
        ativoService.update("1", id, dto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/v1/ativo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        // todo verificar se id isNotEmpyt
        ativoService.delete("1", id);
        return ResponseEntity.ok().build();
    }
}
