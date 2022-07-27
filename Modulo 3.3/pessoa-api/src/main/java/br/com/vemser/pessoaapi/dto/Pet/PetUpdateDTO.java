package br.com.vemser.pessoaapi.dto.Pet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class PetUpdateDTO extends PetCreateDTO{

    @Schema(description = "ID do pet")
    private Integer idPessoa;

}
