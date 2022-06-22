public class ContaCorrente {
    // a variável cheque especial inicial não é manipulada

    Cliente cliente;
    String numeroConta;
    int agencia;
    double saldo;
    double chequeEspecialInicial;
    double chequeEspecial;

    public ContaCorrente(Cliente cliente, String numeroConta, int agencia, double saldo, double chequeEspecialInicial, double chequeEspecial) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.chequeEspecialInicial = chequeEspecialInicial;
        this.chequeEspecial = chequeEspecial;
    }

    public void imprimirContaCorrente(){
        cliente.imprimirCliente();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.printf("\nCONTA DE %s\n", cliente.nome);
        System.out.printf("\t\tNúmero da conta: %s | Agência: %d\n", numeroConta, agencia);
        System.out.printf("\t\tSaldo ->R$%.2f\n\t\tCheque especial ->R$%.2f\n", saldo, chequeEspecial);
    }
    public boolean sacar(double valor){
        System.out.printf("\n[Tentando sacar R$%.2f da conta de %s]\n", valor, cliente.nome);
        if(saldo < 0 || chequeEspecial < 0){
            System.out.println("=-=-=-=-=-=-=-=-=-=\nSaque inválido!\n=-=-=-=-=-=-=-=-=-=");
            return false;
        }
        if(saldo - valor >= 0){
            System.out.printf("Realizando um saque simples!");
            saldo -= valor;
            return true;
        }
        else{
            if(saldo + chequeEspecial - valor >= 0){
                System.out.println("Realizando um saque utilizando o cheque especial!");
                saldo = saldo-valor;
                chequeEspecial = chequeEspecial - (0 - saldo);
                return true;
            }
        }
        System.out.println("Não foi possível realizar um saque!");
        return false;
    }
    public boolean depositar(double valor){
        System.out.println("Depositando!");
        if(valor < 0){
            return false
        }
        if(chequeEspecial < chequeEspecialInicial){
            chequeEspecial+=valor;
        }
        if(chequeEspecial > chequeEspecialInicial){
            saldo += chequeEspecial-chequeEspecialInicial;
            chequeEspecial = chequeEspecialInicial;
        }
        else{
            saldo+= valor;
        }

        return true;
    }
    public double retornarSaldoComChequeEspecial(){
        System.out.println("O saldo com cheque especial é igual a:");
        return saldo + chequeEspecial;
    }
    public boolean transferir(ContaCorrente contaCorrente, double valor){
        System.out.println("Transferindo!");
        if(valor < 0){
            return false;
        }
        if(this.saldo + this.chequeEspecial >= valor){
            System.out.printf("Enviando %.2f de %s para %s", valor, this.cliente.nome, contaCorrente.cliente.nome);
            this.saldo -= valor;
            if(this.saldo < 0){
                chequeEspecial -= (0-this.saldo);
            }
            contaCorrente.saldo += valor;
            return true;
        }
        else {
            System.out.println("Impossível transferir essa quantia :(");
            return false;
        }
    }
}
