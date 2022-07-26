package Exercicios.Exercicio2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static Exercicios.PatternValidator.isValidDate;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                =-=-=-=-=-=-=-=-=-=
                Insira a data 1:
                 .: Utilize o formato dd/MM/yyyy
                """);
        String diaMesAno = scanner.nextLine();
        while (!isValidDate(diaMesAno)) {
            System.out.println("Formato de data inválido, por favor\n.: Utilize o formato dd/MM/yyyy\n->");
            diaMesAno = scanner.nextLine();
        }
        LocalDate data1 = LocalDate.parse(diaMesAno, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("""
                =-=-=-=-=-=-=-=-=-=
                Insira a data 2:
                 .: Utilize o formato dd/MM/yyyy
                 """);
        diaMesAno = scanner.nextLine();
        while (!isValidDate(diaMesAno)) {
            System.out.println("Formato de data inválido, por favor\n.: Utilize o formato dd/MM/yyyy\n->");
            diaMesAno = scanner.nextLine();
        }
        LocalDate data2 = LocalDate.parse(diaMesAno, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Period period = Period.between(data1, data2);
        int anos = period.getYears();
        int meses = period.getMonths();
        int dias = period.getDays();
        System.out.print("Temos ");
        System.out.printf(Math.abs(anos) + " anos ");
        System.out.printf(Math.abs(meses) + " meses ");
        System.out.printf(Math.abs(dias) + " dias ");
        System.out.print("de diferença!");
    }
}