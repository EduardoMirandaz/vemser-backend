package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.ContatoEntity;
import br.com.vemser.pessoaapi.entity.EnderecoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository                                         // entidade | tipo da chave primaria
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

    @Query("SELECT e" +
            " from ENDERECO_PESSOA e")
    Page<EnderecoEntity> enderecoPorCep(Pageable pageable);

    @Query("SELECT e" +
            " from ENDERECO_PESSOA e")
    Page<EnderecoEntity> enderecoPorPais(Pageable pageable);
}

