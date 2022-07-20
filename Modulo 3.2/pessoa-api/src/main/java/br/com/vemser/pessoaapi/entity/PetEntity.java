package br.com.vemser.pessoaapi.entity;


import br.com.vemser.pessoaapi.entity.enums.TipoPet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="PET")

public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_SEQ")
    @SequenceGenerator(name = "PET_SEQ", sequenceName = "seq_pet", allocationSize = 1)
    @Column(name = "id_pet")
    private Integer idPet;

    @Column(name = "idPessoa", insertable = false, updatable = false)
    private Integer idPessoa;

    @Column(name = "nome")
    private String nome;

    private TipoPet tipoPet;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
             // aqui nessa tabela                       na tabela externa
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    private PessoaEntity pessoa;

}
