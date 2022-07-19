package br.com.vemser.pessoaapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "ENDERECO")
public class ENDERECO_PESSOA {

    // Identificadores

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "seq_endereco2", allocationSize = 1)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @NotNull(message = "O endereco deve ser não nulo!")
    @Column(name = "tipo")
    private TipoEndereco tipo;

    @NotNull(message = "O logradouro deve ser não nulo!")
    @NotEmpty(message = "O logradouro deve ser vazio!")
    @Size(min = 5, max = 250, message = "O logradouro deve ter entre 5 e 250 caracteres!")
    @Column(name = "logradouro")
    private String logradouro;

    @NotNull(message = "Número deve ser não nulo!")
    @Column(name = "numero")
    private Integer numero;


    @Column(name = "complemento")
    private String complemento;

    @NotEmpty(message = "Cep não pode estar vazio")
    @NotBlank(message = "Cep não pode estar em branco")
    @Size(min = 8, max = 8, message = "O cep deve conter 8 caracteres")
    @Column(name = "cep")
    private String cep;

    @NotNull(message = "Cidade não deve ser nula")
    @NotBlank(message = "Cidade não deve ser em branco")
    @NotEmpty(message = "Cidade não deve ser vazio")
    @Size(min = 3, max = 250, message = "A cidade deve ter entre 3 e 250 caracteres!")
    @Column(name = "cidade")
    private String cidade;

    @NotNull(message = "Estado não deve ser nulo")
    @NotBlank(message = "Estado não deve ser em branco")
    @NotEmpty(message = "Estado não deve ser vazio")
    @Size(min = 2, max = 250, message = "O estado deve ter entre 2 e 250 caracteres!")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "País não deve ser nulo")
    @NotBlank(message = "País não deve ser em branco")
    @NotEmpty(message = "País não deve ser vazio")
    @Size(min = 3, max = 250, message = "O país deve ter entre 3 e 250 caracteres!")
    @Column(name = "pais")
    private String pais;

}