package br.com.customerregistration.validations;

import br.com.customerregistration.enums.EAreaCodeDDD;

import java.util.Arrays;

public class PhoneValidator {
    public static boolean isPhoneNumberValid(String phoneNumber){

        String trimPhoneNumber = phoneNumber.trim();
        if (!checkCharacters(trimPhoneNumber)) return false;


        int ddd = trimPhoneNumber.length() == 11 ? Integer.parseInt(trimPhoneNumber.substring(0, 2)) : Integer.parseInt(trimPhoneNumber.substring(2, 3));

        EAreaCodeDDD areaCodeDDD = Arrays.stream(EAreaCodeDDD.values())
                .filter(enumValue -> Arrays.asList(enumValue.getDDD()).contains(ddd))
                .findFirst()
                .orElse(null);

        return areaCodeDDD != null;
    }

    public static boolean checkCharacters(String trimPhoneNumber){
        if (trimPhoneNumber.length() != 11 && trimPhoneNumber.length() != 10 || trimPhoneNumber.charAt(0) == '0') return false;

        String notNonNumericCharacter = "^\\d+$";
        if (!trimPhoneNumber.matches(notNonNumericCharacter)) return false;

        String startsWithNineAfterDDD = "^(?!9.{2}).{11}$\n";
        if (trimPhoneNumber.matches(startsWithNineAfterDDD)) return false;

        String areAllTheSameCharacter = "^(.)\\1*$\n";
        return !trimPhoneNumber.matches(areAllTheSameCharacter);
    }
}
