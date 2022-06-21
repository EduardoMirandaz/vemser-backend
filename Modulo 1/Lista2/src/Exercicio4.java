import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        System.out.println("Insira 3 valores para compor um vetor!");
        int[] vetorDeInteiros = new int[3];
        Scanner scanner = new Scanner(System.in);
        int menor = scanner.nextInt(), posicaoMenor = 0;
        scanner.nextLine();
        vetorDeInteiros[0] = menor;
        for(int i = 1; i < 3; i++){
            vetorDeInteiros[i] = scanner.nextInt();
            scanner.nextLine();
            if(vetorDeInteiros[i] < menor){
                menor = vetorDeInteiros[i];
                posicaoMenor = i;
            }
        }
        System.out.println("A posição do menor valor, começando do 0, é: "+ posicaoMenor);
        scanner.close();
    }
}
