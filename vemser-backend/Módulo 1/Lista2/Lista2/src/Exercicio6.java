import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        int[] vetorDeInteiros = new int[]{2,12,15,48,7,2,6,8,9,10};
        Scanner scanner = new Scanner(System.in);
        int numeroASerBuscado = scanner.nextInt();
        scanner.nextLine();
        for (int numero : vetorDeInteiros) {
            if(numero == numeroASerBuscado){
                System.out.printf("Encontrei o número %d na base de dados!\n", numero);
                return;
            }
        }
        System.out.printf("O número %d não está na base de dados =(", numeroASerBuscado);
        scanner.close();
    }
}
