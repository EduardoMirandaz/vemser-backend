public class ContaPoupanca extends Conta implements Impressao{
    static final double JUROS_MENSAL = 1.01;

    public ContaPoupanca(Cliente cliente, String numeroConta, String agencia, double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    public void creditarTaxa(){
        this.setSaldo(this.getSaldo()*JUROS_MENSAL);
    }

    @Override
    public void imprimir(){
        cliente.imprimirCliente();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("\nCONTA POUPANCA DE %s\n", cliente.nome);
        System.out.printf("\t\tNúmero da conta: %s | Agência: %s\n", numeroConta, agencia);
        System.out.printf("\t\tSaldo ->R$%.2f\n", saldo);
    }
}
