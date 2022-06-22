public class Endereco {
    int tipo;
    String logradouro;
    int numero;
    String complemento;
    String cep;
    String cidade;
    String estado;
    String pais;

    public Endereco(int tipo, String logradouro, int numero, String complemento, String cep, String cidade, String estado, String pais) {
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public void imprimirEndereco() {
        System.out.println("\n=-=-=-=-=-=-ENDERECO=-=-=-=-=-=");
        if(tipo == 1){
            System.out.print("\t.: Residencial - ");
        }
        if(tipo == 2){
            System.out.print("\t.: Comercial - ");
        }
        System.out.printf("%s, número %d | %s |\n", logradouro, numero, complemento);
        System.out.printf("\tCEP: %s, %s - %s [%s]\n", cep, cidade, estado, pais);
    }
}
