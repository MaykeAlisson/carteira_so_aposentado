package br.com.carteiradoaposentado.service.impl;

import br.com.carteiradoaposentado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class SegurancaServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        return userRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(format("Não foi encontrado usuario para o email: %s", email)));

    }
}
