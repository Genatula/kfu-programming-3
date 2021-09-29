package ru.kpfu.itis.genatulin.hw2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends AbstractValidator {

    private final Pattern passwordPattern = Pattern.compile("^.*(?=.{8,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$");

    @Override
    public boolean validate(String string) {
        Matcher matcher = passwordPattern.matcher(string);
        return matcher.matches();
    }
}
