import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Exiba quantos você recebe por hora:");
        double horaEmReais = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Insira quantas horas normais trabalhadas:");
        double horasNormaisTrabalhadas = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Insira quantas horas extras 50%:");
        double horasExtras50porCento = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Insira quantas horas extras 100%:");
        double horasExtras100porCento = scanner.nextDouble();
        scanner.nextLine();
        double salarioBruto = ((horasNormaisTrabalhadas*horaEmReais) + (horaEmReais*horasExtras50porCento*1.5) + (horasExtras100porCento * horaEmReais * 2));
        System.out.println(String.format("%.2f",salarioBruto));
    }
}
