package br.com.fred.validations;

import br.com.caelum.stella.validation.CPFValidator;

public class CpfValidator {
    public static boolean isCpfValid(String cpf){
        // class from caelum-stella-core to validate CPF
        CPFValidator cpfValidator = new CPFValidator();
        return cpfValidator.invalidMessagesFor(cpf).isEmpty(); // if list of errors is empty, it is valid
    }
}
