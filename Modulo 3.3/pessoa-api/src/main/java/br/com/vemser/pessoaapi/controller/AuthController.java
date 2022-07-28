package br.com.vemser.pessoaapi.controller;


import br.com.vemser.pessoaapi.dto.LoginDTO;
import br.com.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.vemser.pessoaapi.security.TokenService;
import br.com.vemser.pessoaapi.security.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO login) throws RegraDeNegocioException{

//        Optional<UsuarioEntity> optionalUsuario = usuarioService.findByLoginAndSenha(login.getLogin(), login.getSenha());
//        if(optionalUsuario.isPresent()){
//            log.info("Autenticado com sucesso!");
//            String token = tokenService.getToken(optionalUsuario.get());
//            return token;
//        }
//        throw new RegraDeNegocioException("Usuario ou senha inválidos!");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        login.getLogin(),
                        login.getSenha()
                );

        // o authenticate tenta buscar o usuario e a senha de alguma forma
        Authentication authentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        // Como já passei dessa linha sem exception, já consegui autenticar
        log.info("Autenticado com sucesso!");


        Object usuarioLogado = authentication.getPrincipal();
        UsuarioEntity usuarioEntity = (UsuarioEntity) usuarioLogado;

        String token = tokenService.getToken(usuarioEntity);

        return token;


    }

    @Operation(summary = "Criar novo usuario", description = "Cria um novo usuario e cadastra login e senha no banco de dados!")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso!"),
                    @ApiResponse(responseCode = "400", description = "Nao foi possivel criar esse usuario!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar-usuario")
    public ResponseEntity<String> cadastro(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException{
        return new ResponseEntity<>(usuarioService.cadastrar(loginDTO).getLogin(), HttpStatus.OK);
    }




}