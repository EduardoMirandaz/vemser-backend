import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o total consumido");
        double totalConsumido = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Insira o valor pago");
        double valorPago = scanner.nextDouble();
        scanner.nextLine();
        if(valorPago < totalConsumido){
            System.out.println("O valor pago deve ser maior ou igual ao valor consumido!");
        }
        else if (valorPago == totalConsumido){
            System.out.println("Obrigado e volte sempre!");
        }
        else{
            System.out.println(String.format("O troco foi de: R$%.2f", (valorPago-totalConsumido)));
        }
    }
}
