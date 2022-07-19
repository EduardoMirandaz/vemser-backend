package br.com.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoContato {
    RESIDENCIAL(1),
    COMERCIAL(2);

    private Integer tipo;

    public static TipoContato ofTipo(Integer tipo){
        return Arrays.stream(TipoContato.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }
}