public class ContaCorrente extends Conta implements Impressao{
    private double chequeEspecial;

    public double retornarSaldoComChequeEspecial(){
        return this.getSaldo()+chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public ContaCorrente(Cliente cliente, String numeroConta, String agencia, double saldo, double chequeEspecial) {
        super(cliente, numeroConta, agencia, saldo);
        this.chequeEspecial = chequeEspecial;
    }

    @Override
    public boolean sacar(double valor){
        System.out.printf("\n[Tentando sacar R$%.2f da conta de %s]\n", valor, getCliente().getNome());
        if(saldo <= 0){
            System.out.println("=-=-=-=-=-=-=-=-=-=\nSaque inválido!\n=-=-=-=-=-=-=-=-=-=");
            return false;
        }
        if(saldo - valor >= 0){
            System.out.print("Realizando um saque simples!");
            saldo -= valor;
            return true;
        }
        else{
            if(this.retornarSaldoComChequeEspecial() - valor >= 0){
                saldo = saldo-valor;
                if(saldo >= (-1)*chequeEspecial){
                    System.out.println("Realizando um saque utilizando o cheque especial!");
                    return true;
                }
                else{
                    saldo = saldo + valor;
                    return false;
                }
            }
        }
        System.out.println("Não foi possível realizar um saque!");
        return false;
    }

    @Override
    public void imprimir() {
        cliente.imprimirCliente();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("\nCONTA DE %s\n", getCliente().getNome());
        System.out.printf("\t\tNúmero da conta: %s | Agência: %s\n", numeroConta, agencia);
        System.out.printf("\t\tSaldo ->R$%.2f\n\t\tCheque especial ->R$%.2f\n", saldo, chequeEspecial);
    }
}
