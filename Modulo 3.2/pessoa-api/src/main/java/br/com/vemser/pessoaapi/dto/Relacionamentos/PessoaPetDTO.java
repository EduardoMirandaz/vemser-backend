package br.com.vemser.pessoaapi.dto.Relacionamentos;

import br.com.vemser.pessoaapi.dto.Pessoas.PessoaDTO;
import br.com.vemser.pessoaapi.dto.Pet.PetDTO;
import io.swagger.v3.oas.annotations.media.Schema;
        import lombok.Data;

@Data
public class PessoaPetDTO extends PessoaDTO {
    @Schema(description = "Relacionamento pet com pessoa")
    private PetDTO pet;
}