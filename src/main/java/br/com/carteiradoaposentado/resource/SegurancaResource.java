package br.com.carteiradoaposentado.resource;

import br.com.carteiradoaposentado.commons.dto.UserLoginDto;
import br.com.carteiradoaposentado.commons.dto.UsuarioAcessoDto;
import br.com.carteiradoaposentado.domain.User;
import br.com.carteiradoaposentado.infra.exception.BussinesException;
import br.com.carteiradoaposentado.infra.util.jwt.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/seguranca")
public class SegurancaResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public ResponseEntity<UsuarioAcessoDto> create(@RequestBody @Valid final UserLoginDto dto) {
        try {
            UsernamePasswordAuthenticationToken dadosLogin = UserLoginDto.convert(dto);
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            final User usuario = (User) authenticate.getPrincipal();
            final String token = Token.gerar(usuario.getId(), 1L)
                    .orElseThrow(() -> new BussinesException("Erro ao Gerar token!"));
            final UsuarioAcessoDto usuarioAcessoDto = new UsuarioAcessoDto.Builder()
                    .comIdUser(usuario.getId())
                    .comNome(usuario.getNome())
                    .comToken(token)
                    .build();
            return ResponseEntity.ok().body(usuarioAcessoDto);
        }catch (Exception e){
            throw new BussinesException("Dados de Login Invalidos!");
        }

    }
}
