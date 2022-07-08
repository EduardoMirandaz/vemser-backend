package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Contato;
import br.com.vemser.pessoaapi.entity.TipoContato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();
    @Autowired
    public ContatoRepository() {
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*1*/, 3, "Maicon Gerardi", "contato pessoal whatsapp", "51999885533", TipoContato.COMERCIAL));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*2*/, 5, "Felipe Lemos",  "contato profissional whats", "55999875421",TipoContato.COMERCIAL));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*3*/, 3, "Gonçalo Dias",  "contato residencial 2", "28444659986", TipoContato.RESIDENCIAL));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*4*/, 1, "Felipe Maicon",  "telefone sala 5", "27222336597",TipoContato.COMERCIAL));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*5*/, 4, "Pedro Bonella",  "quarto 415", "11999884455",TipoContato.RESIDENCIAL));
    }


        public Contato create(Contato contato) throws Exception {
        contato.setIdContato(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list() {
        return listaContatos;
    }

    public Contato update(Contato contatoAtualizar) throws Exception {
        return contatoAtualizar;
    }

    public void delete(Contato contatoADeletar) throws Exception {
        listaContatos.remove(contatoADeletar);
    }

    public List<Contato> listById(List<Contato> contatos) {
        return contatos;
    }
}