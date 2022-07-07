package br.com.vemser.pessoaapi.repository;

import br.com.vemser.pessoaapi.entity.Contato;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ContatoRepository {
    private static List<Contato> listaContatos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*1*/, 3, "Maicon Gerardi", "contato pessoal whatsapp", "51999885533"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*2*/, 5, "Felipe Lemos",  "contato profissional whats", "55999875421"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*3*/, 3, "Gonçalo Dias",  "contato residencial 2", "28444659986"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*4*/, 1, "Felipe Maicon",  "telefone sala 5", "27222336597"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet() /*5*/, 4, "Pedro Bonella",  "quarto 415", "11999884455"));
    }

    public Contato create(Contato contato) throws Exception {
        contato.setIdContato(COUNTER.incrementAndGet());
        listaContatos.add(contato);
        return contato;
    }

    public List<Contato> list() {
        return listaContatos;
    }

    public Contato update(Integer id, Contato contatoAtualizar) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não econtrado"));

        contatoRecuperado.setIdPessoa(contatoAtualizar.getIdPessoa());
        contatoRecuperado.setNumero(contatoAtualizar.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizar.getDescricao());
        contatoRecuperado.setNome(contatoAtualizar.getNome());

        return contatoRecuperado;
    }

    public void delete(Integer id) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não econtrado"));
        listaContatos.remove(contatoRecuperado);
    }

    public List<Contato> listById(Integer id) {
        return listaContatos.stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .collect(Collectors.toList());
    }
}