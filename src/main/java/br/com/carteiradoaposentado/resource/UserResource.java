package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.commons.dto.UserDto;
import br.com.carteiradoaposentado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody final UserCreateDto obj) {

        // TODO verificar se obj isEmpyt

        final User user = userService.insert(obj);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    public ResponseEntity<Set<UserDto>> findAll() {

        Set<UserDto> users = userService.findAll().stream().map(UserDto::new).collect(toSet());

        return ok().body(users);

    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable final String id) {

       final  User user = userService.findById(id);

        return ok().body(new UserDto(user));

    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable final String id, @RequestBody final UserCreateDto obj) {

        // TODO verificar se obj e id isEmpyt

        userService.update(id, obj);

        return ok().build();

    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {

        userService.delete(id);

        return ok().build();

    }
}
