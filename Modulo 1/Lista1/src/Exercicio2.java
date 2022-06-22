import java.util.Scanner;

public class Exercicio2 {
    public static boolean ehNotaValida(double nota){
        return (nota >= 0 && nota <= 10);
    }

    public static void main(String[] args) {
        /* Pegando as 4 notas */

        // nota 1
        Scanner scanner = new Scanner(System.in);
        double nota1  = scanner.nextDouble();
        scanner.nextLine();

        // nota 2
        scanner = new Scanner(System.in);
        double nota2  = scanner.nextDouble();
        scanner.nextLine();

        // nota 3
        scanner = new Scanner(System.in);
        double nota3  = scanner.nextDouble();
        scanner.nextLine();

        // nota 4
        scanner = new Scanner(System.in);
        double nota4  = scanner.nextDouble();
        scanner.nextLine();

        // calculando a média final e atribuindo à variável nota
        double nota  = (nota1+nota2+nota3+nota4)/4;

        if(ehNotaValida(nota))
        {
            System.out.println("A média final foi: "+ nota);
            if(nota < 7)
            {
                if(nota <= 5)
                {
                    System.out.println("reprovado!");
                }
                else
                {
                    System.out.println("em exame!");
                }
            }
            else
            {
                System.out.println("Aprovado!");
            }
        }
    }
}
