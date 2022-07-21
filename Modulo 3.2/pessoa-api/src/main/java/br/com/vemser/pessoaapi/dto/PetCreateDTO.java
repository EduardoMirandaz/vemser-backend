package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.enums.TipoPet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PetCreateDTO {

    private Integer idPessoa;

    @NotEmpty(message = "Nome não pode estar vazio")
    @NotBlank(message = "Nome não pode estar em branco")
    @NotNull(message = "Não pode ser nulo")
    @Schema(description = "Nome da pessoa")
    private String nome;

    @NotNull(message = "O pet deve ser não nulo!")
    @Schema(description = "Tipo de pet")
    private TipoPet tipoPet;
}