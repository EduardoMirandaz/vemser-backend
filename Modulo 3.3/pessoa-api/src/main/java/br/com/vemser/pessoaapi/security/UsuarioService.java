package br.com.vemser.pessoaapi.security;


import br.com.vemser.pessoaapi.dto.LoginDTO;
import br.com.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.vemser.pessoaapi.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    // Já vai inicializar o BCrypt por conta da inicialização do construtor
    // do mesmo na criação da classe PasswordEncoder.
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public Optional<UsuarioEntity> findByLogin(String login){
        return usuarioRepository.findByLogin(login);
    }

    public LoginDTO cadastrar(LoginDTO login) {

        //  para fazer sem o autowired
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String senhaEncriptada = bCryptPasswordEncoder.encode(login.getSenha());

        UsuarioEntity usuarioEntity = objectMapper.convertValue(login, UsuarioEntity.class);

        String senhaEncriptografada = passwordEncoder.encode(login.getSenha());

        usuarioEntity.setSenha(senhaEncriptografada);



        return objectMapper.convertValue(usuarioRepository.save(usuarioEntity), LoginDTO.class);
    }
}
