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

    @Column(name = "id_pessoa", insertable = false, updatable = false)
    private Integer idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private TipoPet tipoPet;



    // o cascade CascadeType.ALL nao funciona no relacionamento 1 pra 1, entao devemos apagar de maneira forcada
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
             // aqui nessa tabela                       na tabela externa
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private PessoaEntity pessoa;

}
