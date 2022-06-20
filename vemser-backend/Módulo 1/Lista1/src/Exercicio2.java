import java.util.Scanner;

public class Exercicio2 {
    public static boolean ehNotaValida(double nota){
        return (nota >= 0 && nota <= 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double nota  = scanner.nextDouble();
        scanner.nextLine();
        if(ehNotaValida(nota))
        {
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
