package br.com.fred.auth;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.fred.enums.EAreaCodeDDD;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IAuthManager {
    default Boolean isCustomerNameValid(String customerName) {
        // reference how to validate a name:
        // https://www.geeksforgeeks.org/how-to-validate-a-username-using-regular-expressions-in-java/
        String regex = "^[A-Za-z]\\w{3,29}$";

        Pattern pattern = Pattern.compile(regex);

        if (customerName == null) return false;

        Matcher matcher = pattern.matcher(customerName);

        return matcher.matches();
    }

    default Boolean isCpfValid(String cpf) {
        // class from caelum-stella-core to validate CPF
        CPFValidator cpfValidator = new CPFValidator();
        // if list of errors is empty
        return cpfValidator.invalidMessagesFor(cpf).isEmpty();
    }

    default Boolean isPhoneNumberValid(String phoneNumber) {

        // A number cannot start with 0
        if (phoneNumber.length() != 11 && phoneNumber.length() != 10 || phoneNumber.charAt(0) == '0') return false;

        // Define a regular expression pattern to match if it has a non-number char
        String notNonNumericCharacter = "^\\d+$";
        if (!phoneNumber.matches(notNonNumericCharacter)) return false;

        //if phoneNumber length is equal 11, need to start with 9 after DDD
        String startsWithNineAfterDDD = "^(?!9.{2}).{11}$\n";
        if (phoneNumber.matches(startsWithNineAfterDDD)) return false;

        String areAllTheSameCharacter = "^(.)\\1*$\n";
        if (phoneNumber.matches(areAllTheSameCharacter)) return false;

        int DDD;
        if (phoneNumber.length() == 11)
            DDD = Integer.parseInt(phoneNumber.substring(0, 2));
        else
            DDD = Integer.parseInt(phoneNumber.substring(2, 3));


        // Verify if EAreaCodeDDD enum values has phoneNumber DDD input
        EAreaCodeDDD areaCodeDDD = Arrays.stream(EAreaCodeDDD.values())
                .filter(enumValue -> Arrays.asList(enumValue.getDDD()).contains(DDD))
                .findFirst()
                .orElse(null);

        return areaCodeDDD != null;
    }
}
