public class ContaPagamento extends Conta implements Impressao{
    static final double TAXA_SAQUE = 4.25;

    public ContaPagamento(Cliente cliente, String numeroConta, String agencia, double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    public boolean sacar(double valor) {
        return super.sacar(valor+TAXA_SAQUE);
    }

    public boolean sacar(double valor, boolean ehTransferencia) {
        if(ehTransferencia){
            return super.sacar(valor);
        }
        return sacar(valor);
    }

    @Override
    public boolean transferir(Conta conta, double valor) {
        // se as operacoes de saque ou de depósito derem errado não deveria ser possível transferir.
        if (!this.sacar(valor, true) || !conta.depositar(valor)) {
            System.out.printf("Não é possível transferir R$%.2f", valor);
            return false;
        }
        System.out.printf("Enviando %.2f de %s para %s\n", valor, this.getCliente().getNome(), conta.getCliente().getNome());
        return true;
    }



    @Override
    public void imprimir() {
        cliente.imprimirCliente();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("\nCONTA DE %s\n", getCliente().getNome());
        System.out.printf("\t\tNúmero da conta: %s | Agência: %s\n", numeroConta, agencia);
        System.out.printf("\t\tSaldo ->R$%.2f\n\n", saldo);
    }
}
