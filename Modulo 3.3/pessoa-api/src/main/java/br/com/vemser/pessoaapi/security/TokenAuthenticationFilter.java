package br.com.vemser.pessoaapi.security;

import br.com.vemser.pessoaapi.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

//Classe responsável por filtrar requisicoes
//essa classe não é um bean, entao nao da pra injetar dependencia
//preciso usar new ;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //recuperar token da request
        String token = getTokenFromHeader(request);


        //autenticar o usuario
        Optional<UsuarioEntity> usuarioOptional = tokenService.isValidToken(token);

        if (usuarioOptional.isPresent()){
            UsuarioEntity usuario = usuarioOptional.get();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario.getLogin(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        filterChain.doFilter(request, response);
    }



    private String getTokenFromHeader(HttpServletRequest request){
        // Nessa função, recupero o token "Authorization" do header,
        // que pra nossa aplicação vem sempre com o prefixo Bearer.
        String token = request.getHeader("Authorization");
        if (token == null){
            return null;
        }
        return token.replace(BEARER, "");
    }
}