import java.util.Scanner;


public class Exercicio8 {
    public static void exibirCabecalho(int j){
        switch (j) {
            case 0 -> System.out.print("Matricula: ");
            case 1 -> System.out.print("Media de provas: ");
            case 2 -> System.out.print("Media de trabalhos aluno: ");
            default -> System.out.print("");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = new int[5][4];
        int matriculaComMaiorNota = 0, maiorNotaFinal = 0, somaDasNotasFinais = 0;
        System.out.print("Entre com os 15 valores da matriz, linha por linha\n");
        for(int i = 0; i < 5; i++){
            System.out.printf("===============================\n ____ DADOS ALUNO [%d] ____\n", i);
            for(int j = 0; j < 4; j++){
                // se o j for menor que 3, isto é, se eu estiver nas colunas
                // 0 1 ou 2, apenas leio os valores
                if(j < 3){
                    exibirCabecalho(j);
                    matriz[i][j] = scanner.nextInt();
                    scanner.nextLine();
                }
                //caso o j seja == 3, significa que devo compor a ultima coluna, com as médias finais
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
        System.out.print("\n====--====\n");
        System.out.print("Matriz final:\n");
        System.out.print("Matricula | Media Prova | Media Trabalho | Media Final\n");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 4; j++){
                System.out.printf("%8d\t\t", matriz[i][j]);
            }
            System.out.print("\n");
        }
        System.out.printf("A matrícula que obteve a maior nota final foi: %d\n", matriculaComMaiorNota);
        System.out.printf("A media das notas finais foi: %.2f\n", somaDasNotasFinais*1.0/5);
        scanner.close();
    }
}
