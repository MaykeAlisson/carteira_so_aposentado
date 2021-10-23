package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.commons.dto.UserDto;
import br.com.carteiradoaposentado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user")
    @GetMapping
    public ResponseEntity<Set<UserDto>> findAll() {

        Set<UserDto> users = userService.findAll().stream().map(UserDto::new).collect(toSet());

        return ok().body(users);

    }

    @RequestMapping(value = "/v1/user/{id}")
    @GetMapping
    public ResponseEntity<UserDto> findById(@PathVariable final String id) {

       final  Optional<User> possivelUser = userService.findById(id);

        return possivelUser.isPresent()
                ?  ok().body(new UserDto(possivelUser.get()))
            : ResponseEntity.noContent("Erro");


        return ok().body(users);

    }
}
