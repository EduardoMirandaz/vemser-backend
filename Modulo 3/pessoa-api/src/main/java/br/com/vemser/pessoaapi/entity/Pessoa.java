package br.com.vemser.pessoaapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    // identificador
    private Integer idPessoa;

    @NotEmpty(message = "O nome não deve estar vazio!")
    @NotBlank(message = "O nome não deve estar em branco")
    @NotNull(message = "O nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Informe corretamente a data de nascimento")
    @Past(message = "A data de nascimento deve ser anterior ao dia de hoje!")
    @DateTimeFormat()
    private LocalDate dataNascimento;

    @NotEmpty(message = "CPF não pode ser vazio")
    @NotNull(message = "Insira um cpf com 11 dígitos!")
    @Size(min = 11, max = 11)
    private String cpf;

}