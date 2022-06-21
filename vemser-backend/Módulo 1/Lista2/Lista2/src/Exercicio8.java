import java.util.Scanner;


public class Exercicio8 {
    public static void exibirCabecalho(int j){
        switch (j) {
            case 0:
                System.out.printf("Matricula: ");
                break;
            case 1:
                System.out.printf("Media de provas: ");
                break;

            case 2:
                System.out.printf("Media de trabalhos aluno: ");
                break;
            default:
                System.out.printf("");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = new int[5][4];
        int matriculaComMaiorNota = 0, maiorNotaFinal = 0, somaDasNotasFinais = 0;
        System.out.printf("Entre com os 15 valores da matriz, linha por linha\n");
        for(int i = 0; i < 5; i++){
            System.out.printf("===============================\n ____ DADOS ALUNO [%d] ____\n", i);
            for(int j = 0; j < 4; j++){
                if(j < 3){
                    exibirCabecalho(j);
                    matriz[i][j] = scanner.nextInt();
                    scanner.nextLine();
                }
                else{
                    matriz[i][j] = ((matriz[i][j-2]*6/10) + (matriz[i][j-1]*4/10));
                    if(matriz[i][j] > maiorNotaFinal){
                        matriculaComMaiorNota = matriz[i][j-3];
                        maiorNotaFinal = matriz[i][j];
                    }
                    somaDasNotasFinais += matriz[i][j];
                }
            }
        }
        System.out.printf("\n====--====\n");
        System.out.printf("Matriz final:\n");
        System.out.printf("Matricula | Media Prova | Media Trabalho | Media Final\n");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                System.out.printf("%8d\t\t", matriz[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("A matrícula que obteve a maior nota final foi: %d\n", matriculaComMaiorNota);
        System.out.printf("A media das notas finais foi: %d\n", somaDasNotasFinais/5);
        scanner.close();
    }
}
