package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.PessoaEntity;
import br.com.vemser.pessoaapi.entity.enums.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                         // entidade | tipo da chave primaria
public interface ContatoRepository extends JpaRepository<ContatoEntity, Integer> {

//    @Query("SELECT c" +
//            " from Contato c" +
//            " where c.tipoContato = ?1") //indico que será feito um mapeamento para o primeiro parametro do metodo
//    List<PessoaEntity> getContatosPorTipo(TipoContato tipoContato);
}
