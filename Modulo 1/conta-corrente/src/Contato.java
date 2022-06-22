public class Contato {
    String descricao;
    String telefone;
    int tipo;

    public Contato(String descricao, String telefone, int tipo) {
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public void imprimirContato() {
        System.out.println("\n=-=-=-=-=-=-CONTATO=-=-=-=-=-=");
        System.out.println("\t\t" + descricao);
        if(tipo==1){
            System.out.printf("\t\tFone residencial: %s\n", telefone);
        }
        if(tipo==2){
            System.out.printf("\t\tFone comercial: %s\n\n", telefone);
        }
    }
}
