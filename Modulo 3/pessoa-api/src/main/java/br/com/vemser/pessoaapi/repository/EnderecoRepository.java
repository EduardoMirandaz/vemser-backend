package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Endereco;
import br.com.vemser.pessoaapi.entity.TipoEndereco;
import br.com.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository

public class EnderecoRepository {
    private static List<Endereco> listaEnderecos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecos.add(new Endereco(3, COUNTER.incrementAndGet() /*1*/,     TipoEndereco.RESIDENCIAL, "Rua Jose Pedro",14,"Ao lado da casa verde","23954-879","Pindamonhangaba", "Rio de Janeiro", "Brasil"));
        listaEnderecos.add(new Endereco(5, COUNTER.incrementAndGet() /*2*/, TipoEndereco.COMERCIAL, "Avenida Paulo Dias",16,"sobrado azul","54654-963","Almerinha",  "Espirito Santo", "Brasil"));
        listaEnderecos.add(new Endereco(1, COUNTER.incrementAndGet() /*3*/, TipoEndereco.COMERCIAL, "Avenida Lacerda Junior",13,"conjunto residencial 3","21321-63","Gonçalo Dias",  "Bahia", "Brasil"));
    }

    public Endereco create(Endereco endereco) throws RegraDeNegocioException {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }

    public List<Endereco> list() {
        return listaEnderecos;
    }

    public Endereco update(Endereco enderecoAtualizar) throws RegraDeNegocioException {


        return enderecoAtualizar;
    }

    public void delete(Endereco enderecoADeletar) throws RegraDeNegocioException {
        listaEnderecos.remove(enderecoADeletar);
    }

    public List<Endereco> listById(List<Endereco> enderecos) {
        return enderecos;
    }
}
