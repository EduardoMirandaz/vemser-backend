package br.com.vemser.pessoaapi.dto.Pet;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetDTO extends PetCreateDTO {
    @Schema(description = "ID do pet")
    private Integer idPet;

    @Schema(description = "ID do pet")
    private Integer idPessoa;


}
