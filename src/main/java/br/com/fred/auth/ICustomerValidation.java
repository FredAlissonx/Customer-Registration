package br.com.fred.auth;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;
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
    default Boolean isPhoneNumberValid(String data){
        return true;
    }
}
