import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        System.out.println("Controle de eleição!");
        System.out.println("Insira o numero total de votantes");
        Scanner scanner = new Scanner(System.in);
        double totalDeEleitores = scanner.nextInt();
        scanner.nextLine();
        double votosBrancos = scanner.nextInt();
        scanner.nextLine();
        double votosNulos = scanner.nextInt();
        scanner.nextLine();
        double votosValidos = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Percentual votos brancos:");
        System.out.println(String.format("=-=== %.2f%% ===-=", 100*(votosBrancos/totalDeEleitores)));
        System.out.println("Percentual votos nulos:");
        System.out.println(String.format("=-=== %.2f%% ===-=", 100*(votosNulos/totalDeEleitores)));
        System.out.println("Percentual votos validos:");
        System.out.println(String.format("=-=== %.2f%% ===-=", 100*(votosValidos/totalDeEleitores)));
    }
}
