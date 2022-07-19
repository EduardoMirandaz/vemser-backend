package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                         // entidade | tipo da chave primaria
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
