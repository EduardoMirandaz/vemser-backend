package br.com.vemser.pessoaapi.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DadosPessoaisDTO {

    @Schema(description = "CNH da pessoa")
    private String cnh;
    @Schema(description = "CPF da pessoa")
    private String cpf;
    @Schema(description = "Nome da pessoa")
    private String nome;
    @Schema(description = "Nome da mae")
    private String nomeMae;
    @Schema(description = "Nome do pai")
    private String nomePai;
    @Schema(description = "RG da pessoa")
    private String rg;
    @Schema(description = "Sexo da pessoa")
    private SexoEnumDTO sexo;
    @Schema(description = "Titulo de eleitor da pessoa")
    private String tituloEleitor;

}
