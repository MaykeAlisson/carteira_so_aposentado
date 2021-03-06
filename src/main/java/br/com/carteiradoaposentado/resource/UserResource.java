package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import br.com.carteiradoaposentado.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity<UsuarioAcessoDto> create(@RequestBody @Valid final UserCreateDto obj) {

        final User user = userService.insert(obj);

        final String token = Token.gerar(user.getId(), 1L)
                .orElseThrow(() -> new BussinesException("Erro ao Gerar token!"));
        final UsuarioAcessoDto usuarioAcessoDto = new UsuarioAcessoDto.Builder()
                .comIdUser(user.getId())
                .comNome(user.getNome())
                .comToken(token)
                .build();

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioAcessoDto);

    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody @Valid final UserCreateDto obj) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }

        final String userId = Token.getUserId();

        if (!Objects.equals(userId, id)) {
            throw new BussinesException("Id usuario n??o corresponde ao informado!");
        }

        userService.update(id, obj);

        return ok().build();

    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new BussinesException("id obrigatorio!");
        }

        final String userId = Token.getUserId();

        if (!Objects.equals(userId, id)) {
            throw new BussinesException("Id usuario n??o corresponde ao informado!");
        }

        userService.delete(id);

        return ok().build();

    }

}
