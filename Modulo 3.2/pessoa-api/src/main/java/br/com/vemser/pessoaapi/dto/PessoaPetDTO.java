package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
        import lombok.Data;

@Data
public class PessoaPetDTO extends PessoaDTO {
    @Schema(description = "Relacionamento pet com pessoa")
    private PetDTO pet;
}