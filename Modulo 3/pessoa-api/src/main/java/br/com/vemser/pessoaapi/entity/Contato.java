package br.com.vemser.pessoaapi.entity;


public class Contato {

    private Integer idContato;
    private Integer idPessoa;

    private String nome;
    private String descricao;
    private String numero;

    public Contato() {
    }

    public Contato(Integer idContato, Integer idPessoa, String nome, String descricao, String numero) {
        this.idContato = idContato;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.descricao = descricao;
        this.numero = numero;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "idContato=" + idContato +
                ", nome='" + nome + '\'' +
                ", descricao=" + descricao +
                ", numero='" + numero + '\'' +
                '}';
    }
}