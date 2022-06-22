public class Cliente {
    String nome;
    String cpf;
    Contato[] contatos;
    Endereco[] enderecos;

    public Cliente(String nome, String cpf, Contato[] contatos, Endereco[] enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.contatos = contatos;
        this.enderecos = enderecos;
    }

    public void imprimirContatos(){
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("=-=-=-=-=-=-=-=-\nimprimindo contatos de %s\n", nome);
        for (Contato contato : contatos) {
            contato.imprimirContato();
        }
    }
    public void imprimirEnderecos(){
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("=-=-=-=-=-=-=-=-\nimprimindo enderecos de %s\n", nome);
        for (Endereco endereco : enderecos) {
            endereco.imprimirEndereco();
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
