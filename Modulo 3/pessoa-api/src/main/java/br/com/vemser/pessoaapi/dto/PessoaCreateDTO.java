package br.com.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @NotEmpty(message = "Nome não pode estar vazio")
    @NotBlank(message = "Nome não pode estar em branco")
    @NotNull(message = "Não pode ser nulo")
    private String nome;

    @NotNull(message = "Informe sua data de nascimento")
    @Past(message = "Você não pode nascer no futuro!")
    private LocalDate dataNascimento;

    @NotNull(message = "Não pode ser nulo")
    @NotEmpty(message = "Nào pode ser vazio")
    @Size(min = 11,max = 11, message = "Deve conter 11 caracteres")
    private String cpf;

    @NotNull(message = "Não pode ser nulo")
    @NotEmpty(message = "Nào pode ser vazio")
    private String email;
}