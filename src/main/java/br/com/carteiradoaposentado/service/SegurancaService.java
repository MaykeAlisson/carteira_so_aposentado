package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.UserLoginDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegurancaService {

    @Autowired
    private UserRepository userRepository;

    public UsuarioAcessoDto verificaAcesso(final UserLoginDto dto){

        userRepository.findById()

    }
}
