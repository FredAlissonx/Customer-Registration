package br.com.customerregistration.validations;

import java.util.regex.Pattern;

public class NameValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^(?U)[\\p{L}'`\\-]+(?: [\\p{L}'`\\-]+)*$");

    public static boolean isNameValid(String name) {
        return matchesAllowedCharacters(name);
    }

    private static boolean matchesAllowedCharacters(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }
}

