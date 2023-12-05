package br.com.fred.validations;

import java.util.regex.Pattern;

public class NameValidator {
    public static boolean isNameValid(String name){

        if (name == null) return false;
        return matchingName(name);
    }
    public static boolean matchingName(String name){
        // Name cannot have names with special characters outside the ASCII character set.
        Pattern pattern = Pattern.compile("^(?U)[\\p{L}'`\\-]+(?: [\\p{L}'`\\-]+)*$");

        return pattern.matcher(name).matches();
    }
}
