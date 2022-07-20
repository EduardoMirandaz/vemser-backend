package br.com.vemser.pessoaapi.entity.PrimaryKeys;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProfessorPK implements Serializable {

    @Column(name = "id_professor")
    private Integer id_professor;

    @Column(name = "id_universidade")
    private Integer id_universidade;


}
