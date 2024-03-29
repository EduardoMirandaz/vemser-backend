package br.com.vemser.pessoaapi.dto.Pessoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaDTO extends PessoaCreateDTO {
    @Schema(description = "ID da pessoa")
    private Integer idPessoa;
}