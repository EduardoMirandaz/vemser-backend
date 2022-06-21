import java.util.Scanner;

public class Exercicio7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = new int[4][4];
        int contMaiorQue10 = 0;
        System.out.printf("Entre com os 16 valores da matriz 4x4, linha por linha\n");
        for(int i = 0; i < 4; i++){
            System.out.printf("------Linha _[%d]_------\n=-=-=-=-=-=-=-=-=-=-=-=\n", i);
            for(int j = 0; j < 4; j++){
                System.out.printf("|||| Coluna ´[%d]` ||||\n\t-> ", j);
                matriz[i][j] = scanner.nextInt();
                scanner.nextLine();
                if(matriz[i][j] > 10){
                    contMaiorQue10 ++;
                }
            }
        }
        System.out.printf("\n====--====\n");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.printf("%d ", matriz[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("====--====\nA matriz acima possui %d valores maiores que 10", contMaiorQue10);
        scanner.close();
    }
}
