package com.carteiradoaposentado.carteiraDoAposentado.resource;

import com.carteiradoaposentado.carteiraDoAposentado.commons.dto.UserDto;
import com.carteiradoaposentado.carteiraDoAposentado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
