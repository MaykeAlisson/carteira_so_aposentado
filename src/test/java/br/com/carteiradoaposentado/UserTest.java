package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void cadastrarNovoUsuario() {
        final UserCreateDto user = new UserCreateDto("Nome", "nome@email.com", "123456");
        final User insert = userService.insert(user);
        Assertions.assertNotNull(insert);
    }

}
