package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                         // entidade | tipo da chave primaria
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {


    List<PessoaEntity> findByNomeContainsIgnoreCase(String nome);
    PessoaEntity findByCpf(String nome);


    // jpql methods

    // o from deve ser identico ao mapeamento da Entity, nesse caso "PESSOA"

    // o where é case sensitive, deve estar igual ao atributo DO JAVA!

    // p é o alias de pessoa, nesse caso o objeto inteiro
//    @Query("SELECT p" +
//            " from Pessoa p" +
//            " where p.cpf = ?1") //indico que será feito um mapeamento para o primeiro parametro do metodo
//    List<PessoaEntity> listPessoaByCPF(String cpf);


}
