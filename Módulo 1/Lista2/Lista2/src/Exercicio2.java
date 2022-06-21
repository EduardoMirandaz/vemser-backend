import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Insira o numero a ser acertado:\n->");
        int numeroASerAcertado = scanner.nextInt();
        scanner.nextLine();
        int chute;
        do{
            System.out.printf("Tente acertar o número que estou pensando:\n-> ");
            chute = scanner.nextInt();
            scanner.nextLine();
            if(chute < numeroASerAcertado){
                System.out.printf("O número a ser encontrado é maior que %d\n", chute);
            }
            else if(chute > numeroASerAcertado){
                System.out.printf("O número a ser encontrado é menor que %d\n", chute);
            }
        }while(chute!=numeroASerAcertado);

        System.out.printf("Parabéns, você acertou!!");
        scanner.close();
    }
}
