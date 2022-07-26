package Exercicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternValidator {

    private static final String COMPLETE_DATE_VALIDATOR =
            "dd/MM/uuuu";


    public static boolean isValidDate(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(COMPLETE_DATE_VALIDATOR)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}