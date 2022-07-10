package br.com.vemser.pessoaapi.entity;


import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contato {

    // identificadores
    private Integer idContato;
    private Integer idPessoa;


    @NotNull(message = "O nome não deve ser nulo")
    @NotEmpty(message = "O nome não deve estar vazio")
    private String nome;


    @NotNull(message = "A descrição nao pode ser nula")
    @NotEmpty(message = "A descrição nao pode estar vazia")
    private String descricao;

    @NotEmpty(message = "O número não pode ser vazio")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode ser em branco")
    @Size(max = 13, message = "O número conter no máximo 13 caracteres")
    private String numero;

    @NotNull(message = "O tipo de contato não pode ser nulo!")
    private TipoContato tipoContato;

}