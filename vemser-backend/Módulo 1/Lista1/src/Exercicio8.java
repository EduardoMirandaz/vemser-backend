import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        System.out.println("Vamos calcular a área de um retângulo!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o lado MAIOR do retângulo:");
        double ladoMaior = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Insira o lado MENOR do retângulo:");
        double ladoMenor = scanner.nextDouble();
        scanner.nextLine();
        System.out.println(String.format("Área do retângulo -> %.3f", ladoMaior*ladoMenor));

    }
}
