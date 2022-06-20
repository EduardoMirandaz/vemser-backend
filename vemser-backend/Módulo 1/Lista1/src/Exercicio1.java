import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        // preciso usar o nextLine vazio depois de ler um int, char, double, etc
        // se eu ler uma string nao preciso de dar nextLine vazio

        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Insira sua idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Insira sua cidade:");
        String cidade = scanner.nextLine();
        System.out.println("Insira seu estado:");
        String estado = scanner.nextLine();
        System.out.println("Ola, seu nome é "+nome+", voce tem "+idade+" anos, é da cidade de "+cidade+
                ", situada no estado de "+estado+".");

    }
}
