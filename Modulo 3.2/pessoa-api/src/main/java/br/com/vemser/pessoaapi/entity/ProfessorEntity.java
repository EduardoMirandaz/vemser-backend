package br.com.vemser.pessoaapi.entity;


import br.com.vemser.pessoaapi.entity.PrimaryKeys.ProfessorPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Professor")
public class ProfessorEntity {

    @EmbeddedId
    private ProfessorPK professorPK;

    @Column(name = "nome")
    private String nome;
    @Column(name = "salario")
    private Double salario;
}
