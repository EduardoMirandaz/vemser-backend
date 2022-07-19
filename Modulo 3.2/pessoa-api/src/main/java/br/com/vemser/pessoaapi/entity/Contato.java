package br.com.vemser.pessoaapi.entity;


import lombok.*;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="CONTATO")
public class Contato {

    // identificadores
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "seq_contato", allocationSize = 1)
    @Column(name = "id_contato")
    private Integer idContato;

    @Column(name = "id_pessoa")
    private Integer idPessoa;


//    @NotNull(message = "O nome não deve ser nulo")
//    @NotEmpty(message = "O nome não deve estar vazio")
//    private String nome;


    @NotNull(message = "A descrição nao pode ser nula")
    @NotEmpty(message = "A descrição nao pode estar vazia")
    @Column(name = "descricao")
    private String descricao;

    @NotEmpty(message = "O número não pode ser vazio")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode ser em branco")
    @Size(max = 13, message = "O número conter no máximo 13 caracteres")
    @Column(name = "numero")
    private String numero;

    @NotNull(message = "O tipo de contato não pode ser nulo!")
    @Column(name = "tipo")
    private TipoContato tipoContato;

}