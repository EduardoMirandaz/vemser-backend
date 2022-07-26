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
                ->""");
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
                ->""");
        diaMesAno = scanner.nextLine();
        while (!isValidDate(diaMesAno)) {
            System.out.println("Formato de data inválido, por favor\n.: Utilize o formato dd/MM/yyyy\n->");
            diaMesAno = scanner.nextLine();
        }
        LocalDate data2 = LocalDate.parse(diaMesAno, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(data1.isAfter(data2)){
            data2 = data2.plusYears(1);
        }
        Period period = Period.between(data1, data2);
        int anos = period.getYears();
        int meses = period.getMonths();
        int dias = period.getDays();
        System.out.print("Temos ");
        System.out.printf(anos + " anos ");
        System.out.printf(meses + " meses ");
        System.out.printf(dias + " dias ");
        System.out.print("de diferença!");
    }
}