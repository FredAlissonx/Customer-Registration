package br.com.fred.validations;

import br.com.fred.enums.EAreaCodeDDD;

import java.util.Arrays;

public class PhoneValidator {
    public static boolean isPhoneNumberValid(String phoneNumber){

        if (!checkCharacters(phoneNumber)) return false;

        // Getting DDD from different phone numbers
        int ddd = phoneNumber.length() == 11 ? Integer.parseInt(phoneNumber.substring(0, 2)) : Integer.parseInt(phoneNumber.substring(2, 3));

        // Verify if EAreaCodeDDD enum values has phoneNumber DDD input
        EAreaCodeDDD areaCodeDDD = Arrays.stream(EAreaCodeDDD.values())
                .filter(enumValue -> Arrays.asList(enumValue.getDDD()).contains(ddd))
                .findFirst()
                .orElse(null);
        return areaCodeDDD != null;
    }

    public static boolean checkCharacters(String phoneNumber){
        // A number cannot start with 0
        if (phoneNumber.length() != 11 && phoneNumber.length() != 10 || phoneNumber.charAt(0) == '0') return false;

        // Define a regular expression pattern to match if it has a non-number char
        String notNonNumericCharacter = "^\\d+$";
        if (!phoneNumber.matches(notNonNumericCharacter)) return false;

        //if phoneNumber length is equal 11, need to start with 9 after DDD
        String startsWithNineAfterDDD = "^(?!9.{2}).{11}$\n";
        if (phoneNumber.matches(startsWithNineAfterDDD)) return false;

        String areAllTheSameCharacter = "^(.)\\1*$\n";
        return !phoneNumber.matches(areAllTheSameCharacter);
    }
}
