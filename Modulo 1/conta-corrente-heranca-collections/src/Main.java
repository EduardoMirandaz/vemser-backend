import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*** +++++++++ CRIANDO CONTATOS +++++++++ ***/
        Contato contato1 = new Contato("Eduardo Whats", "22656499856", 1);
        Contato contato2 = new Contato("Joao Victor novo", "11589786453", 1);
        Contato contato3 = new Contato("Mariana BCC", "21998754623", 1);
        Contato contato4 = new Contato("Clayton Lava-Jato", "28998543225", 2);
        Contato contato5 = new Contato("Daniel Whats", "22656499856", 1);
        Contato contato6 = new Contato("Raquel novo", "11589786453", 2);
        Contato contato7 = new Contato("Ivan BCC", "546879623", 1);
        Contato contato8 = new Contato("Thainá Cafeteria", "28998543225", 2);
        /*** COLOCANDO CONTATOS NAS LISTAS DE CONTATOS ***/

        List<Contato> cContatosEduardo = new ArrayList<>(List.of(contato1, contato2));
        List<Contato> cContatosFlavio = new ArrayList<>(List.of(contato3, contato4));
        List<Contato> cContatosGustavo = new ArrayList<>(List.of(contato5, contato6));
        List<Contato> cContatosIvan = new ArrayList<>(List.of(contato7, contato8));

        /*** +++++++++ CRIANDO ENDERECOS +++++++++ ***/
        Endereco endereco1 = new Endereco(1,"Avenida Joao Dos Santos", 32, "Ao lado da casa verde",
                "23.023-49", "Bauru", "São Paulo", "Brasil");
        Endereco endereco2 = new Endereco(2,"Rua Alameda dos Crisantemos", 62, "Em frente ao restaurante Comer Bem",
                "90.193-46", "Londrina", "Paraná", "Brasil");
        Endereco endereco3 = new Endereco(2,"Rua Pedro Estelita", 562, "Em cima do bar da Penha",
                "13.132-70", "Vitoria", "Espirito Santo", "Brasil");
        Endereco endereco4 = new Endereco(1,"Rua Mateus Duarte", 80, "Ao lado do quiosque da Leia",
                "29.300-00", "Guarapari", "Espírito Santo", "Brasil");
        Endereco endereco5 = new Endereco(1,"Avenida Adenilso Simao", 32, "Ao lado da casa verde",
                "23.023-49", "Bauru", "São Paulo", "Brasil");
        Endereco endereco6 = new Endereco(2,"Rua Eduardo Simões", 62, "Em frente ao restaurante Comer Bem",
                "90.193-46", "Londrina", "Paraná", "Brasil");
        Endereco endereco7 = new Endereco(2,"Rua Fernanda Braz", 562, "Em cima da casa vermelha",
                "13.132-70", "Vitoria", "Espirito Santo", "Brasil");
        Endereco endereco8 = new Endereco(1,"Nogueira de Oliveira", 80, "Ao lado do DIA",
                "29.300-00", "Guarapari", "Espírito Santo", "Brasil");

        /*** COLOCANDO ENDERECOS NAS LISTAS DE ENDERECOS ***/

        List<Endereco> eEnderecosEduardo = new ArrayList<Endereco>(List.of(endereco1, endereco2));
        List<Endereco> eEnderecosFlavio = new ArrayList<Endereco>(List.of(endereco3, endereco4));
        List<Endereco> eEnderecosGustavo = new ArrayList<Endereco>(List.of(endereco5, endereco6));
        List<Endereco> eEnderecosIvan = new ArrayList<Endereco>(List.of(endereco7, endereco8));

        /*** +++++++++ CRIANDO CLIENTES +++++++++ ***/
        Cliente cEdu0001 = new Cliente("Eduardo Miranda", "546.444.879-12", cContatosEduardo, eEnderecosEduardo);
        Cliente cFla0001 = new Cliente("Flavio Bodao", "942.425.001-19", cContatosFlavio, eEnderecosFlavio);
        Cliente cGus0001 = new Cliente("Gustavo Valadao", "666.245.871-12", cContatosGustavo, eEnderecosGustavo);
        Cliente cIva0001 = new Cliente("Ivan Jhonson", "642.400.012-12", cContatosIvan, eEnderecosIvan);

        /*** +++++++++ CRIANDO CONTAS +++++++++ ***/
        ContaCorrente ccEdu0001 = new ContaCorrente(cEdu0001, "19325-9", "921", 3000, 200);
        ContaCorrente ccFla0001 = new ContaCorrente(cFla0001, "57964-0", "7198", 2500, 1000);
        ContaPoupanca cpGus0001 = new ContaPoupanca(cGus0001, "1231412", "512512", 87223);
        ContaPoupanca cpIva0001 = new ContaPoupanca(cIva0001, "997543", "9050552", 10002456);
        ContaPagamento cpagEdu0001 = new ContaPagamento(cEdu0001, "2210", "x333", 54876.52);


        /**
         ** VERIFICA SE HÁ ALGO NULO DEPOIS DA CRIAÇÃO DAS CONTAS.
         ** A FUNÇÃO UTILIZA A NOÇÃO DE POLIMORFISMO POIS CRIA UMA LISTA
         ** COM OBJETOS DA CLASSE CONTA, SENDO ESSES OBJETOS ContaCorrente e ContaPoupanca
         **/

        // Verifica conta por conta se há algum atributo nulo, pensando em futuras modificações de input.
        Conta[] listaDeContasParaVerificacao = new Conta[]{ccEdu0001, ccFla0001, cpGus0001, cpIva0001};
        for (Conta contaASerVerificada : listaDeContasParaVerificacao) {
            if (contaASerVerificada == null) {
                return;
            }
            else
            {
                if (contaASerVerificada.numeroConta == null ||
                        contaASerVerificada.cliente == null ||
                        contaASerVerificada.cliente.getNome() == null ||
                        contaASerVerificada.cliente.getCpf() == null ||
                        contaASerVerificada.cliente.getEnderecos() == null ||
                        contaASerVerificada.cliente.getContatos() == null)
                {
                    return;
                }
                else {
                    for (Endereco enderecoParaVerificacao : contaASerVerificada.cliente.getEnderecos()) {
                        if (enderecoParaVerificacao == null) {
                            return;
                        }
                        for (Contato contatoParaVerificacao : contaASerVerificada.getCliente().getContatos()) {
                            if (contatoParaVerificacao == null) {
                                return;
                            }
                        }
                    }
                }
            }
        }

        /*
         *******************************************
         -----|> TESTES COM CONTAS CORRENTES <|-----
         *******************************************
         */

        System.out.println("=-=-=-=-CONTAS CORRENTE CADASTRADAS=-=-=-=-\n");

//        Aqui temos uma transação inválida
        ccEdu0001.transferir(ccFla0001, 4000);
        ccFla0001.imprimir();
        ccEdu0001.imprimir();
//        Aqui temos uma transação válida
        ccFla0001.transferir(ccEdu0001, 100);
        ccFla0001.imprimir();
        ccEdu0001.imprimir();
//        Aqui temos um depósito
        ccFla0001.depositar(5100.21);
        ccFla0001.imprimir();
//        Aqui temos um saque válido
        ccEdu0001.sacar(60.51);
        ccEdu0001.imprimir();
//        Aqui temos um saque inválido
        ccEdu0001.sacar(60000);
        ccEdu0001.imprimir();
//        Aqui temos um saque válido que chega no cheque especial e negativa o saldo
        ccEdu0001.sacar(3050);
        ccEdu0001.imprimir();
//        Aqui temos um saque negativo inválido
        ccEdu0001.sacar(-3050);
        ccEdu0001.imprimir();
//        Aqui temos um deposito negativo inválido
        ccEdu0001.depositar(-3050);
        ccEdu0001.imprimir();
/////*************************************************************/
/////*************************************************************/
////         /*
////         *******************************************
////         -----|> TESTES COM CONTAS POUPANCAS <|-----
////         *******************************************
////         */
////
        System.out.println("=-=-=-=-CONTAS POUPANCA CADASTRADAS=-=-=-=-\n");

//        Aqui temos uma transação inválida
        cpGus0001.transferir(cpGus0001, 544100000);
        cpIva0001.imprimir();
        cpGus0001.imprimir();
//        Aqui temos uma transação válida
        cpIva0001.transferir(cpIva0001, 100);
        cpIva0001.imprimir();
        cpGus0001.imprimir();
//        Aqui temos um depósito válido
        cpIva0001.depositar(5100.21);
        cpIva0001.imprimir();
//        Aqui temos um depósito inválido
        cpIva0001.depositar(-5100.21);
        cpIva0001.imprimir();
//        Aqui temos um saque válido
        cpGus0001.sacar(60.51);
        cpGus0001.imprimir();
//        Aqui temos um saque inválido
        cpGus0001.sacar(60000);
        cpGus0001.imprimir();
//        Aqui temos uma creditação de taxa na poupança
        cpGus0001.creditarTaxa();
        cpGus0001.imprimir();
//        Aqui temos um saque negativo inválido
        cpGus0001.sacar(-3050);
        cpGus0001.imprimir();
//        Aqui temos um deposito negativo inválido
        cpGus0001.depositar(-3050);
        cpGus0001.imprimir();
//
//
//
//        System.out.println("=-=-=-=-TESTES CONTAS POUPANCA PAGAMENTO E CORRENTE =-=-=-=-\n");
//
        // Exemplo para mostrar que o saldo cobra taxa e a transferência não!
        cpagEdu0001.setSaldo(72);
        cpagEdu0001.sacar(72);
        cpagEdu0001.transferir(cpIva0001, 72);
        cpIva0001.imprimir();
//        Aqui temos uma transação válida
        cpIva0001.transferir(cpIva0001, 100);
        cpIva0001.imprimir();
        cpGus0001.imprimir();
//        Aqui temos um depósito válido
        cpIva0001.depositar(5100.21);
        cpIva0001.imprimir();
//        Aqui temos um depósito inválido
        cpIva0001.depositar(-5100.21);
        cpIva0001.imprimir();
//        Aqui temos um saque válido
        cpGus0001.sacar(60.51);
        cpGus0001.imprimir();
//        Aqui temos um saque inválido
        cpGus0001.sacar(60000);
        cpGus0001.imprimir();
//        Aqui temos uma creditação de taxa na poupança
        cpGus0001.creditarTaxa();
        cpGus0001.imprimir();
//        Aqui temos um saque negativo inválido
        cpGus0001.sacar(-3050);
        cpGus0001.imprimir();
//        Aqui temos um deposito negativo inválido
        cpGus0001.depositar(-3050);
        cpGus0001.imprimir();

    }
}