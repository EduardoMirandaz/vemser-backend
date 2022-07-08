package br.com.vemser.pessoaapi.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Contato {

    @NotNull
    private Integer idContato;
    private Integer idPessoa;

    private String nome;
    @NotNull
    @NotEmpty
    private String descricao;
    @Size(max = 13)
    @NotNull
    private String numero;
    @NotNull
    private TipoContato tipoContato;

    public Contato() {
    }

    public Contato(Integer idContato, Integer idPessoa, String nome, String descricao, String numero, TipoContato tipoContato) {
        this.idContato = idContato;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.descricao = descricao;
        this.numero = numero;
        this.tipoContato = tipoContato;
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

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "idContato=" + idContato +
                ", idPessoa=" + idPessoa +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", numero='" + numero + '\'' +
                ", tipoContato=" + tipoContato +
                '}';
    }
}