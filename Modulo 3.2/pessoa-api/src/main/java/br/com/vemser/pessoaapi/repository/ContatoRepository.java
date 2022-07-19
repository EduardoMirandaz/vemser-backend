package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                         // entidade | tipo da chave primaria
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
