package br.com.fred.auth;

import br.com.caelum.stella.validation.CPFValidator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ICustomerValidation {
    default Boolean isDataNull(String[] data){
        return data.length < 7;
    }
    default Boolean isCustomerNameValid(String customerName){
        // reference how to validate a name:
        // https://www.geeksforgeeks.org/how-to-validate-a-username-using-regular-expressions-in-java/
        String regex = "^[A-Za-z]\\w{3,29}$";

        Pattern pattern = Pattern.compile(regex);

        if (customerName == null) return false;

        Matcher matcher = pattern.matcher(customerName);

        return matcher.matches();
    }
    default Boolean isCpfValid(String cpf){
        // class from caelum-stella-core to validate CPF
        CPFValidator cpfValidator = new CPFValidator();
        // if list of errors is empty
        return cpfValidator.invalidMessagesFor(cpf).isEmpty();
    }
    default Boolean isPhoneNumberValid(String phoneNumber){

        // Replaces all non-numerics characters (including spaces, tab, etc.)
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        // verify if it has valid length
        if (!(phoneNumber.length() >= 10 && phoneNumber.length() <= 11)) return false;
        // verify if phoneNumber begin with 9
        if (phoneNumber.length() == 11 && Integer.parseInt(phoneNumber.substring(2,3)) != 9) return false;

        // verify if all character in phoneNumber are the same
        Pattern pattern = Pattern.compile(phoneNumber.charAt(0) + "{"+phoneNumber.length()+"}");

        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) return false;

        // valid DDD
        Integer[] dddCode = {
                11, 12, 13, 14, 15, 16, 17, 18, 19,
                21, 22, 24, 27, 28, 31, 32, 33, 34,
                35, 37, 38, 41, 42, 43, 44, 45, 46,
                47, 48, 49, 51, 53, 54, 55, 61, 62,
                64, 63, 65, 66, 67, 68, 69, 71, 73,
                74, 75, 77, 79, 81, 82, 83, 84, 85,
                86, 87, 88, 89, 91, 92, 93, 94, 95,
                96, 97, 98, 99
        };
        // verify if contains phoneNumber DDD
        if (!Arrays.asList(dddCode).contains(Integer.parseInt(phoneNumber.substring(0, 2)))) return false;

        //if the number has length 10, it's not a smartphone number that why after DDD needs to be 2, 3, 4, 5, or 7
        Integer[] prefix = {2, 3, 4, 5, 7};
        // after all validations right, return true
        return phoneNumber.length() == 10 && Arrays.asList(prefix).contains(Integer.parseInt(phoneNumber.substring(0, 2)));
    }
}
