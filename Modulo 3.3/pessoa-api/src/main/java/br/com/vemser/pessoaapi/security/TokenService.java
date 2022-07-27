package br.com.vemser.pessoaapi.security;


import br.com.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.vemser.pessoaapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UsuarioService usuarioService;

    public String getToken(UsuarioEntity usuarioEntity) {
        String tokenTexto = usuarioEntity.getLogin() + ";" + usuarioEntity.getSenha();//user;123
        String token = Base64.getEncoder().encodeToString(tokenTexto.getBytes());
        return token;
    }

    public Optional<UsuarioEntity> isValidToken(String token) {
        if (token == null) {
            return Optional.empty();
        }
        byte[] decodedBytes = Base64.getUrlDecoder().decode(token);
        String decodedString = new String(decodedBytes);
        String[] split = decodedString.split(";");
        Optional<UsuarioEntity> optionalUsuario = usuarioService.findByLoginAndSenha(split[0], split[1]);
        return optionalUsuario;
    }

}
