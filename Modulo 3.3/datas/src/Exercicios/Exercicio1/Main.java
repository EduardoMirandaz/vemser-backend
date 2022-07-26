package Exercicios.Exercicio1;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Scanner;

import static Exercicios.PatternValidator.isValidDayMonth;


public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                =-=-=-=-=-=-=-=-=-=
                Insira sua data de aniversário:
                 .: Utilize o formato dd/MM
                ->""");

        String diaMes = scanner.nextLine();
        while (!isValidDayMonth(diaMes)) {
            System.out.println("Formato de data inválido, por favor\n.: Utilize o formato dd/MM\n->");
            diaMes = scanner.nextLine();
        }
        LocalDate dataAniversario = LocalDate.parse(diaMes+"/"+LocalDate.now().get(ChronoField.YEAR), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataAtual = LocalDate.now();
        // Se a data atual já é posterior à data do aniversário, isto é
        // o aniversário da pessoa já passou, ela só o fará novamente no
        // próximo ano, por isso adiciono um ano na data de aniversário dela.
        if(dataAtual.isAfter(dataAniversario)){
            dataAniversario = dataAniversario.plusYears(1);
        }
        Period period = Period.between(dataAtual, dataAniversario);
        int dias = period.getDays();
        int meses = period.getMonths();

        System.out.println("Falta(am)");
        System.out.println(meses + " meses");
        System.out.println(dias + " dias ");
        System.out.print("para o seu aniversário!");
    }

}