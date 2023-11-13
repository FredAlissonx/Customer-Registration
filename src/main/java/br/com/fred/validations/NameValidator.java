package br.com.fred.validations;

import java.util.regex.Pattern;

public class NameValidator {
    public static boolean isNameValid(String firstName, String lastName){

        if (!(firstName != null || lastName != null)) return false;

        return matchingName(firstName, lastName);
    }
    public static boolean matchingName(String firstName, String lastName){
        // Name cannot have names with special characters outside the ASCII character set.
        Pattern pattern = Pattern.compile("^(?U)[\\p{L}'`\\-]+(?: [\\p{L}'`\\-]+)*$");

        return pattern.matcher(firstName).matches()
                && pattern.matcher(lastName).matches();
    }
}
