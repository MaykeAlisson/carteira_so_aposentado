package br.com.carteiradoaposentado;

import br.com.carteiradoaposentado.commons.dto.UserCreateDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void cadastrarNovoUsuario() {
        final UserCreateDto user = new UserCreateDto("Nome", "nome@email.com", "123456");
        final User insert = userService.insert(user);
        Assertions.assertNotNull(insert);
    }

    @Test
    @Order(2)
    void cadastrarNovoUsuarioComMesmoEmail() {
        final UserCreateDto user = new UserCreateDto("MesmoEmail", "nome@email.com", "123456");
        Assertions.assertThrows(BussinesException.class, () -> userService.insert(user));
    }

    @Test
    void buscarPorId() {
        final User user = userService.findById("1");
        Assertions.assertNotNull(user);
    }

    @Test
    void buscarPorIdInvalido() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findById("50"));
    }

    @Test
    void atualizarNomeESenha() {
        final User user = userService.findById("1");
        final String nome = user.getNome();
        final String senha = user.getSenha();
        final UserCreateDto userUpdate = new UserCreateDto("NovoNome", "nome@email.com", "novaSenha");
        userService.update("1", userUpdate);
        final User userAtualizar = userService.findById("1");
        final String novoNome = userAtualizar.getNome();
        final String novaSenha = userAtualizar.getSenha();
        Assertions.assertFalse(Objects.equals(nome, novoNome) && Objects.equals(senha, novaSenha));
    }

    @Test
    void deletar() {
        final User user = userService.findById("2");
        userService.delete(user.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findById(user.getId()));
    }

}
