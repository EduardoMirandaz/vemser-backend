package br.com.vemser.pessoaapi.dto.Relatorios;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatorioPessoaCompletaDTO {
    private Integer id;
    private String nome;
    private String email;
    private String numeroTelefone;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String nomeDoPet;

}
