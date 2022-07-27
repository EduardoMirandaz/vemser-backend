package Exercicios.Exercicio4;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    private static final LocalDateTime HORA_DO_SHOW_DO_SAFADEUS_NO_BRASIL = LocalDateTime.of(2024, 9, 14, 14, 30);
    private static final int ano_em_segundos = 31536000;
    private static final int mes_em_segundos = 2592000;
    private static final int dia_em_segundos = 86400;
    private static final int hora_em_segundos = 3600;
    private static final int minuto_em_segundos = 60;


    public static void main(String[] args){
        LocalDateTime agora = LocalDateTime.now();
        long durationInSeconds = Duration.between(agora, HORA_DO_SHOW_DO_SAFADEUS_NO_BRASIL).getSeconds();
        System.out.println("Faltam::");
        int anos = 0, meses = 0, dias = 0, minutos = 0, horas = 0;
        while(durationInSeconds >= 0){
            durationInSeconds -= ano_em_segundos;
            if(durationInSeconds >= 0)
            {
                anos+=1;
                continue;
            }
            durationInSeconds += ano_em_segundos;
            durationInSeconds -= mes_em_segundos;
            if(durationInSeconds >= 0)
            {
                meses+=1;
                continue;
            }
            durationInSeconds += mes_em_segundos;
            durationInSeconds -= dia_em_segundos;
            if(durationInSeconds >= 0)
            {
                dias+=1;
                continue;
            }
            durationInSeconds += dia_em_segundos;
            durationInSeconds -= hora_em_segundos;
            if(durationInSeconds >= 0)
            {
                horas+=1;
                continue;
            }
            durationInSeconds += hora_em_segundos;
            durationInSeconds -= minuto_em_segundos;
            if(durationInSeconds >= 0)
            {
                minutos+=1;
                continue;
            }
            durationInSeconds += minuto_em_segundos;
            break;
        }
        System.out.println(anos + " ano(s).");
        System.out.println(meses + " mes(es).");
        System.out.println(dias + " dia(as).");
        System.out.println(horas + " hora(as).");
        System.out.println(minutos + " minuto(os).");
        System.out.println(durationInSeconds + " segundo(os).");
    }
}