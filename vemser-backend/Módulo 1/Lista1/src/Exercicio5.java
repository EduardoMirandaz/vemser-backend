import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double horaEmReais = scanner.nextDouble();
        scanner.nextLine();
        double horasNormaisTrabalhadas = scanner.nextDouble();
        scanner.nextLine();
        double horasExtras50porCento = scanner.nextDouble();
        scanner.nextLine();
        double horasExtras100porCento = scanner.nextDouble();
        scanner.nextLine();
        double salarioBruto = ((horasNormaisTrabalhadas*horaEmReais) + (horaEmReais*horasExtras50porCento*1.5) + (horasExtras100porCento * horaEmReais * 2));
        System.out.println(String.format("%.2f",salarioBruto));
    }
}
