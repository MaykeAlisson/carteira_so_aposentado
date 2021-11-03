package br.com.carteiradoaposentado.service;

import br.com.carteiradoaposentado.commons.dto.UserLoginDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.exception.ResourceNotFoundException;
import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

import static br.com.carteiradoaposentado.infra.util.UtilCrypto.encriptar;
import static java.lang.String.format;

@Service
public class SegurancaService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UsuarioAcessoDto verificaAcesso(final UserLoginDto dto){

        final User user = userRepository.buscarPorEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(format("Não foi encontrado usuario para o email: %s", dto.getEmail())));

        if(!Objects.equals(user.getSenha(), encriptar(dto.getSenha()))) throw new BussinesException("Senha invalida!");

        // cria token

        return null;

    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        return userRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(format("Não foi encontrado usuario para o email: %s", email)));

    }
}
