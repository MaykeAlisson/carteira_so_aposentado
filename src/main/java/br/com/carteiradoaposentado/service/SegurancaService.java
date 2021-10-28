package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.UserLoginDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.carteiradoaposentado.infra.util.UtilCrypto.encriptar;
import static java.lang.String.format;

@Service
public class SegurancaService {

    @Autowired
    private UserRepository userRepository;

    public UsuarioAcessoDto verificaAcesso(final UserLoginDto dto){

        final User user = userRepository.buscarPorEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(format("Não foi encontrado usuario para o email: %s", dto.getEmail())));

        if(!Objects.equals(user.getSenha(), encriptar(dto.getSenha()))) throw new BussinesException("Senha invalida!");

        // cria token


    }
}
