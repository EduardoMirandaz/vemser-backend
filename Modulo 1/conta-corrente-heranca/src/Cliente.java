public class Cliente {
    private String nome;
    private String cpf;
    private Contato[] contatos;
    private Endereco[] enderecos;

    public Cliente(String nome, String cpf, Contato[] contatos, Endereco[] enderecos) {
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

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    public Endereco[] getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Endereco[] enderecos) {
        this.enderecos = enderecos;
    }

    public void imprimirContatos(){
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("=-=-=-=-=-=-=-=-\nimprimindo contatos de %s\n", nome);
        for (Contato contato : contatos) {
            if(contato != null){
                contato.imprimirContato();
            }
        }
    }
    public void imprimirEnderecos(){
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
