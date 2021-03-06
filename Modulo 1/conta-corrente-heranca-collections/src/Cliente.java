import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private List<Contato> contatos;
    private List<Endereco> enderecos;

    public Cliente(String nome, String cpf, List<Contato> contatos, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.contatos = contatos;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        if(contatos != null){
            this.contatos = contatos;
        }
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        if(enderecos != null){
            this.enderecos = enderecos;
        }
    }

    public void imprimirContatos(){
        if(contatos == null){
            System.out.println("Contatos inválidos");
            return;
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("=-=-=-=-=-=-=-=-\nimprimindo contatos de %s\n", nome);
        for (Contato contato : contatos) {
            if(contato != null){
                contato.imprimirContato();
            }
        }
    }
    public void imprimirEnderecos(){
        if(enderecos == null){
            System.out.println("Enderecos inválidos");
            return;
        }
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("=-=-=-=-=-=-=-=-\nimprimindo enderecos de %s\n", nome);
        for (Endereco endereco : enderecos) {
            if(endereco != null){
                endereco.imprimirEndereco();
            }
        }
    }
    public void imprimirCliente(){
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.println("\t || CLIENTE ||\n");
        System.out.println(nome);
        System.out.printf("CPF .: %s", cpf);
        imprimirEnderecos();
        imprimirContatos();
    }

}
