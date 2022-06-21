import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        int idade = 0, quantidadeJogadores = 0, idadeJogadorMaisVelho = 0;
        double somaAlturaJogadores = 0.0, mediaAlturaJogadores, peso, altura, alturaMaior = 0.0 ,pesoJogadorMaisPesado=0.0;

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Insira o nome do jogador: \n->");
        String nome = scanner.nextLine();
        String nomeMaisVelho = nome;
        String nomeMaisPesado = nome;

        while(!"SAIR".equals(nome)){
            System.out.printf("Insira a altura do jogador em metros: \n->");
            altura = scanner.nextDouble();
            scanner.nextLine();
            System.out.printf("Insira a idade do jogador: \n->");
            idade = scanner.nextInt();
            scanner.nextLine();
            System.out.printf("Insira o peso do jogador em quilos: \n->");
            peso = scanner.nextDouble();
            scanner.nextLine();

            quantidadeJogadores++;
            if(altura > alturaMaior) {
                alturaMaior = altura;
            }
            if(idade > idadeJogadorMaisVelho) {
                idadeJogadorMaisVelho = idade;
                nomeMaisVelho = nome;
            }
            if(peso > pesoJogadorMaisPesado) {
                pesoJogadorMaisPesado = peso;
                nomeMaisPesado = nome;
            }
            somaAlturaJogadores += altura;
            System.out.printf("Insira o nome do jogador: \n->");
            nome = scanner.nextLine();
        }
        System.out.printf("Tivemos [%d] jogadores cadastrados\n", quantidadeJogadores);
        System.out.printf("O jogador mais alto tinha %.2f de altura\n", alturaMaior);
        System.out.printf("O jogador mais velho é %s com %d anos\n", nomeMaisVelho, idadeJogadorMaisVelho);
        System.out.printf("O jogador mais pesado é %s com %.2f quilos\n", nomeMaisPesado, pesoJogadorMaisPesado);
        mediaAlturaJogadores = somaAlturaJogadores / quantidadeJogadores;
        System.out.printf("A media das alturas dos jogadores é %.2f\n", mediaAlturaJogadores);
        scanner.close();
    }
}
