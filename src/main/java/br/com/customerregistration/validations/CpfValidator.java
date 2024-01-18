package br.com.customerregistration.validations;

import br.com.caelum.stella.validation.CPFValidator;

public class CpfValidator {
    public static boolean isCpfValid(String cpf) {
        return isCpfValidUsingExternalLibrary(cpf.trim());
    }

    private static boolean isCpfValidUsingExternalLibrary(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        return cpfValidator.invalidMessagesFor(cpf.trim()).isEmpty();
    }
}

