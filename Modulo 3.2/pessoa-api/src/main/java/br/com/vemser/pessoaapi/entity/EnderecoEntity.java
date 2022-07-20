package br.com.vemser.pessoaapi.entity;

import br.com.vemser.pessoaapi.entity.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ENDERECO_PESSOA")
public class EnderecoEntity {

    // Identificadores

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "seq_endereco2", allocationSize = 1)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "id_pessoa")
    private Integer id_pessoa;

    @Column(name = "tipo")
    private TipoEndereco tipo;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;


    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;


    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "Pessoa_x_Pessoa_Endereco",
            joinColumns = @JoinColumn(name="id_endereco"),
            inverseJoinColumns = @JoinColumn(name="id_pessoa"))
    private Set<PessoaEntity> pessoas;

    // para realizar apenas consultas posso adicionar o parâmetro mapped by

}