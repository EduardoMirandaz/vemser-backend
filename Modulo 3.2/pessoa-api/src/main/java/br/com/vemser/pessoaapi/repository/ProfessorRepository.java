package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.PrimaryKeys.ProfessorPK;
import br.com.vemser.pessoaapi.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                         // entidade | tipo da chave primaria
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, ProfessorPK> {

}
