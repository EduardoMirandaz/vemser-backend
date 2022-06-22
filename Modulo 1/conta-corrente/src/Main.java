public class Main {
    public static void main(String[] args) {
        Contato contato1 = new Contato("Eduardo Whats", "22656499856", 1);
        Contato contato2 = new Contato("Joao Victor novo", "11589786453", 1);
        Contato contato3 = new Contato("Mariana BCC", "21998754623", 1);
        Contato contato4 = new Contato("Clayton Lava-Jato", "28998543225", 2);

        Contato[] cContatosEduardo = new Contato[]{contato1, contato2};
        Contato[] cContatosFlavio = new Contato[]{contato3, contato4};

        Endereco endereco1 = new Endereco(1,"Avenida Joao Dos Santos", 32, "Ao lado da casa verde",
                "23.023-49", "Bauru", "São Paulo", "Brasil");
        Endereco endereco2 = new Endereco(2,"Rua Alameda dos Crisantemos", 62, "Em frente ao restaurante Comer Bem",
                "90.193-46", "Londrina", "Paraná", "Brasil");
        Endereco endereco3 = new Endereco(2,"Rua Pedro Estelita", 562, "Em cima do bar da Penha",
                "13.132-70", "Vitoria", "Espirito Santo", "Brasil");
        Endereco endereco4 = new Endereco(1,"Rua Mateus Duarte", 80, "Ao lado do quiosque da Leia",
                "29.300-00", "Guarapari", "Espírito Santo", "Brasil");

        Endereco[] eEnderecosEduardo = new Endereco[]{endereco1, endereco2};
        Endereco[] eEnderecosFlavio = new Endereco[]{endereco3, endereco4};

        Cliente cEdu0001 = new Cliente("Eduardo Miranda", "546.444.879-12", cContatosEduardo, eEnderecosEduardo);
        Cliente cFla0001 = new Cliente("Flavio Bodao", "942.425.001-19", cContatosFlavio, eEnderecosFlavio);

        ContaCorrente ccEdu0001 = new ContaCorrente(cEdu0001, "19325-9", 921, 3000, 200);
        ContaCorrente ccFla0001 = new ContaCorrente(cFla0001, "57964-0", 7198, 2500, 1000);

        // Verifica conta por conta e todos os atributos se são nulos
        ContaCorrente[] listaDeContasParaVerificacao = new ContaCorrente[]{ccEdu0001, ccFla0001};
        for (ContaCorrente contaASerVerificada : listaDeContasParaVerificacao) {
            if (contaASerVerificada == null) {
                return;
            }
            else
            {
                if (contaASerVerificada.numeroConta == null ||
                        contaASerVerificada.cliente == null ||
                        contaASerVerificada.cliente.nome == null ||
                        contaASerVerificada.cliente.cpf == null ||
                        contaASerVerificada.cliente.enderecos == null ||
                        contaASerVerificada.cliente.contatos == null)
                {
                    return;
                }
                else {
                    for (Endereco enderecoParaVerificacao : contaASerVerificada.cliente.enderecos) {
                        if (enderecoParaVerificacao == null) {
                            return;
                        }
                        for (Contato contatoParaVerificacao : contaASerVerificada.cliente.contatos) {
                            if (contatoParaVerificacao == null) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("=-=-=-=-CONTAS CORRENTES CADASTRADAS=-=-=-=-\n");

//        Aqui temos uma transação inválida
        ccEdu0001.transferir(ccFla0001, 4000);
        ccFla0001.imprimirContaCorrente();
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos uma transação válida
        ccFla0001.transferir(ccEdu0001, 100);
        ccFla0001.imprimirContaCorrente();
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos um depósito
        ccFla0001.depositar(5100.21);
        ccFla0001.imprimirContaCorrente();
//        Aqui temos um saque válido
        ccEdu0001.sacar(60.51);
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos um saque inválido
        ccEdu0001.sacar(60000);
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos um saque válido que chega no cheque especial e negativa o saldo
        ccEdu0001.sacar(3050);
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos um saque negativo inválido
        ccEdu0001.sacar(-3050);
        ccEdu0001.imprimirContaCorrente();
//        Aqui temos um deposito negativo inválido
        ccEdu0001.depositar(-3050);
        ccEdu0001.imprimirContaCorrente();

    }
}