package Exercicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternValidator {

    // simple regex
    //private static final String USERNAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

    // strict regex
    private static final String DAY_MONTH_VALIDATOR =
            "^\\d{2}/\\d{2}+$";

    private static final String COMPLETE_DATE_VALIDATOR =
            "^\\d{2}/\\d{2}/\\d{4}+$";

    private static final Pattern completeDateValidator = Pattern.compile(COMPLETE_DATE_VALIDATOR);
    private static final Pattern dayMonthValidator = Pattern.compile(DAY_MONTH_VALIDATOR);


    public static boolean isValidDayMonth(final String date) {
        Matcher matcher = dayMonthValidator.matcher(date);
        return matcher.matches();
    }

    public static boolean isValidDate(final String date) {
        Matcher matcher = completeDateValidator.matcher(date);
        return matcher.matches();
    }

}