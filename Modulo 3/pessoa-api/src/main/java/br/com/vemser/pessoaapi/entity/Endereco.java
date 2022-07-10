package br.com.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
public class Endereco {

    // Identificadores
    private Integer idPessoa;
    private Integer idEndereco;

    @NotNull(message = "O endereco deve ser não nulo!")
    private TipoEndereco tipo;

    @NotNull(message = "O logradouro deve ser não nulo!")
    @NotEmpty(message = "O logradouro deve ser vazio!")
    @Size(min = 5, max = 250, message = "O logradouro deve ter entre 5 e 250 caracteres!")
    private String logradouro;

    @NotNull(message = "Número deve ser não nulo!")
    private Integer numero;

    private String complemento;

    @NotEmpty(message = "Cep não pode estar vazio")
    @NotBlank(message = "Cep não pode estar em branco")
    @Size(min = 8, max = 8, message = "O cep deve conter 8 caracteres")
    private String cep;

    @NotNull(message = "Cidade não deve ser nula")
    @NotBlank(message = "Cidade não deve ser em branco")
    @NotEmpty(message = "Cidade não deve ser vazio")
    @Size(min = 3, max = 250, message = "A cidade deve ter entre 3 e 250 caracteres!")
    private String cidade;

    @NotNull(message = "Estado não deve ser nulo")
    @NotBlank(message = "Estado não deve ser em branco")
    @NotEmpty(message = "Estado não deve ser vazio")
    @Size(min = 2, max = 250, message = "O estado deve ter entre 2 e 250 caracteres!")
    private String estado;

    @NotNull(message = "País não deve ser nulo")
    @NotBlank(message = "País não deve ser em branco")
    @NotEmpty(message = "País não deve ser vazio")
    @Size(min = 3, max = 250, message = "O país deve ter entre 3 e 250 caracteres!")
    private String pais;

}