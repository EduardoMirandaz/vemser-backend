package Exercicio3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        LocalDateTime dataAtual = LocalDateTime.now();
        dataAtual = dataAtual.plusDays(15);
        dataAtual = dataAtual.plusHours(10);

        System.out.printf("The day of today 15 days and 10 hours later will be wednesday ");
        System.out.printf(dataAtual.getDayOfWeek().toString().toLowerCase(Locale.ROOT) + " and we'll be in the ");
        System.out.printf(String.valueOf(dataAtual.getDayOfYear()));
        System.out.printf("º day of the year!");
    }
}