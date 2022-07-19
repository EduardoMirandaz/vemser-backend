package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.ENDERECO_PESSOA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                         // entidade | tipo da chave primaria
public interface EnderecoRepository extends JpaRepository<ENDERECO_PESSOA, Integer> {
}

