package ru.kpfu.itis.genatulin.genatulin.hw2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator {

    private final Pattern emailPattern = Pattern.compile("^(\\w([_\\-])?)*\\w*@(\\w-?)*\\w*(\\.\\w{2,})+$");

    @Override
    public boolean validate(String string) {
        Matcher matcher = emailPattern.matcher(string);
        return matcher.matches();
    }
}
