package br.com.vemser.pessoaapi.dto;

import br.com.vemser.pessoaapi.entity.enums.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ContatoCreateDTO {

    @NotNull(message = "A descrição nao pode ser nula")
    @NotEmpty(message = "A descrição nao pode estar vazia")
    @Schema(description = "Descricao do contato")
    private String descricao;

    @NotEmpty(message = "O número não pode ser vazio")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode ser em branco")
    @Size(max = 13, message = "O número conter no máximo 13 caracteres")
    @Schema(description = "Numero ")
    private String numero;

    @Schema(description = "Tipo contato")
    @NotNull(message = "O tipo de contato não pode ser nulo!")
    private TipoContato tipoContato;
}
