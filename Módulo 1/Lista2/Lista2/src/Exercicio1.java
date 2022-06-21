import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Insira o nome do produto:\n-> ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Insira o valor do produto:\n-> ");
        double valorProduto = scanner.nextDouble();
        scanner.nextLine();
        System.out.printf("Produto .: %s\n", nomeProduto);
        System.out.printf("Preco   .: R$%.2f\n=-=-=-=-=-=-=-=-=\n", valorProduto);
        System.out.printf("__[- Promocao %s -]__\n", nomeProduto);
        System.out.print("=======-=-=-=-==========\n");
        for(double i = 0.05, counter = 1; i < 0.5; i+=0.05, counter += 1){
            System.out.printf("%.0f x R$ %.2f = R$ %.2f\n", counter, (valorProduto*(1-i)), valorProduto*(1-i)*counter);
        }
    }
}
