package br.com.vemser.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="PESSOA")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQ")
    @SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "seq_pessoa2", allocationSize = 1)
    @Column(name = "idPessoa")
    private Integer idPessoa;

    @Column(name = "id_pet", insertable = false, updatable = false)
    private Integer idPet;

    @Column(name = "nome")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    // evita os loops de mapeamento
    @JsonIgnore
    // uma pessoa para muitos endereços           // pega a ligação que ja foi feita na pessoa
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa",
            cascade = CascadeType.ALL, //
            orphanRemoval = true) // remove os filhos
    private Set<ContatoEntity> contatos;


    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "Pessoa_x_Pessoa_Endereco",
            joinColumns = @JoinColumn(name="idPessoa"),
            inverseJoinColumns = @JoinColumn(name="id_endereco"))
    private Set<EnderecoEntity> enderecos;


    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    // aqui nessa tabela                       na tabela externa
    @JoinColumn(name = "id_pet", referencedColumnName = "id_pet")
    private PetEntity pet;


}
