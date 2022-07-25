package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.dto.Relatorios.RelatorioPessoaCompletaDTO;
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

    @Query(value = "select new br.com.vemser.pessoaapi.dto.Relatorios.RelatorioPessoaCompletaDTO(" +
            "p.id, " +
            "p.nome, " +
            "p.email, " +
            "c.numero, " +
            "e.cep, " +
            "e.cidade, " +
            "e.estado, " +
            "e.pais, " +
            "pett.nome" +
            ") " +
            "from PESSOA p " +
            "left join p.contatos c " +
            "left join p.enderecos e " +
            "left join p.pet pett")
    List<RelatorioPessoaCompletaDTO> exibirPessoaCompleta();


    /*
     com ':' antes são os parametros da query que vieram do java, com '?1', '?2', '?n' indico qual
     o parametro de referência.
    */

    // no FROM devemos sempre utilizar o mapeamento da entity


    // jpql methods

    // o from deve ser identico ao mapeamento da Entity, nesse caso "PESSOA"

    // o where é case sensitive, deve estar igual ao atributo DO JAVA!

    // p é o alias de pessoa, nesse caso o objeto inteiro


}
