import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Insira 20 números 1 a 1:");
        int [] vetorDeInteiros = new int[20];
        for (int i = 0; i < 20; i++){
            vetorDeInteiros[i] = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.printf("O vetor invertido fica:");
        System.out.printf("[ ");
        for (int i = 19; i >= 0; i--){
            System.out.printf("%d ", vetorDeInteiros[i]);
        }
        System.out.printf("]");
        scanner.close();
    }
}
