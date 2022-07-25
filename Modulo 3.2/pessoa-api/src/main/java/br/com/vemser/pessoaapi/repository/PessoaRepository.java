package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.PessoaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                         // entidade | tipo da chave primaria
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {


    List<PessoaEntity> findByNomeContainsIgnoreCase(String nome);
    PessoaEntity findByCpf(String nome);


//    SELECT P.ID_PESSOA, P.NOME,EP.ID_ENDERECO, EP.LOGRADOURO, PET.ID_PET, PET.NOME, C.ID_CONTATO, C.NUMERO
//    FROM VEM_SER.PESSOA P
//    LEFT JOIN PESSOA_X_PESSOA_ENDERECO PXPE ON (P.ID_PESSOA = PXPE.ID_PESSOA)
//    LEFT JOIN ENDERECO_PESSOA EP ON (PXPE.ID_ENDERECO = EP.ID_ENDERECO)
//    LEFT JOIN CONTATO C ON (C.ID_PESSOA  = P.ID_PESSOA)
//    LEFT JOIN PET PET ON (PET.ID_PESSOA  = P.ID_PESSOA)


    /*
     com : antes são os parametros da query que vieram do java, com ?1, ?2, ?n indico qual
     o parametro de referência.
    */

    // no FROM devemos sempre utilizar o mapeamento da entity


    // jpql methods

    // o from deve ser identico ao mapeamento da Entity, nesse caso "PESSOA"

    // o where é case sensitive, deve estar igual ao atributo DO JAVA!

    // p é o alias de pessoa, nesse caso o objeto inteiro

//    @Query("SELECT p" +
//            " from Pessoa p" +
//            " where p.cpf = ?1") //indico que será feito um mapeamento para o primeiro parametro do metodo
//    List<PessoaEntity> listPessoaByCPF(String cpf);


}
