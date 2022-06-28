public abstract class Conta implements Movimentacao{
    Cliente cliente;
    String numeroConta;
    String agencia;
    double saldo;

    public Conta(Cliente cliente, String numeroConta, String agencia, double saldo) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public boolean sacar(double valor){
        System.out.printf("\n[Tentando sacar R$%.2f da conta de %s]\n", valor, getCliente().getNome());
        if(saldo <= 0){
            System.out.println("=-=-=-=-=-=-=-=-=-=\nSaque inválido!\n=-=-=-=-=-=-=-=-=-=");
            return false;
        }
        if(saldo - valor >= 0){
            System.out.printf("Realizando um saque simples!");
            saldo -= valor;
            System.out.println("Saquei, tudo ok!");
            return true;
        }
        System.out.println("Não foi possível realizar um saque!");
        return false;
    }
    @Override
    public boolean depositar(double valor){
        System.out.println("Depositando!");
        if(valor <= 0){
            System.out.printf("Não é possível depositar R$%.2f", valor);
            return false;
        }
        this.setSaldo(this.getSaldo() + valor);
        System.out.printf("R$%.2f depositados na conta de %s!\n", valor, getCliente().getNome());
        return true;
    }
    @Override
    public boolean transferir(Conta conta, double valor) {
        System.out.println("Transferindo!");
        // se as operacoes de saque ou de depósito derem errado não deveria ser possível transferir.
        if (!this.sacar(valor) || !conta.depositar(valor)) {
            System.out.printf("Não é possível transferir R$%.2f", valor);
            return false;
        }
        System.out.printf("Enviando %.2f de %s para %s\n", valor, this.getCliente().getNome(), conta.getCliente().getNome());
        return true;

    }

}
