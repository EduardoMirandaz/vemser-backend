package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    @Schema(description = "ID do endereco")
    private Integer idEndereco;


}
