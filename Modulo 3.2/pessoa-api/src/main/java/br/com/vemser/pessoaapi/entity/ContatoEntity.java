package br.com.vemser.pessoaapi.entity;


import br.com.vemser.pessoaapi.entity.enums.TipoContato;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CONTATO")
public class ContatoEntity {

    // identificadores
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "seq_contato", allocationSize = 1)
    @Column(name = "id_contato")
    private Integer idContato;

    @Column(name = "idPessoa", insertable = false, updatable = false)
    private Integer idPessoa;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private TipoContato tipoContato;

    // muitos contatos pra uma pessoa:
//    FetchType.EAGER -> traz sempre que o programa roda
//    FetchType.LAZY -> traz somente quando solicitado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")
    private PessoaEntity pessoa;

}