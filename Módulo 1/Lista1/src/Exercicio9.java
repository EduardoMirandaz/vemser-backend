import java.util.Scanner;

public class Exercicio9 {
    public static void main(String[] args) {
        System.out.println("Insira a idade em anos:");
        Scanner scanner = new Scanner(System.in);
        int idadeEmAnos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira a idade em meses:");
        int idadeEmMeses = scanner.nextInt();
        System.out.println("Insira a idade em dias:");
        scanner.nextLine();
        int idadeEmDias = scanner.nextInt();
        scanner.nextLine();
        int idadeFinalEmDias = idadeEmDias + (idadeEmMeses*30) + (idadeEmAnos * 365);
        System.out.println("A idade dessa pessoa em dias é:" + idadeFinalEmDias);
    }
}
