package br.com.vemser.pessoaapi.security;


import br.com.vemser.pessoaapi.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${segredo}")
    private String segredo;

    public String getToken(UsuarioEntity usuarioEntity) {
        Date now = Date.from(ZonedDateTime.now().toInstant());
        String token = Jwts.builder()
                .setIssuer("pessoa-api")
                .claim(Claims.ID, usuarioEntity.getIdUsuario()) // o id no nosso jwt é o id do usuario recuperado
                .setIssuedAt(now)
                .setExpiration(Date.from(ZonedDateTime.now().plusDays(5).toInstant()))
                .signWith(SignatureAlgorithm.HS256, segredo)
                .compact();

        // adicionando o bearer no token (pego ele da variável protected do token authentication.
        return TokenAuthenticationFilter.BEARER + token;

    }

    public UsernamePasswordAuthenticationToken isValidToken(String token) {
        if (token == null) {
            return null;
        }

        // Recuperando o payload do token
        Claims body = Jwts.parser()
                .setSigningKey(segredo)
                //valida o token (já verifica a expiração)
                .parseClaimsJws(token)
                .getBody();

        // id retornado no payload, recuperado como inteiro!
        Integer idUsuario = body.get(Claims.ID, Integer.class);

        if(idUsuario != null){
            // para passar na autenticação, o jwt precisa desse objeto UsernamePasswordAuthenticationToken
            return new UsernamePasswordAuthenticationToken(idUsuario, null, Collections.emptyList());
        }
        return null;
    }

}
