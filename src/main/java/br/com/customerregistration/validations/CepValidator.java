package br.com.customerregistration.validations;

import java.util.regex.Pattern;

public class CepValidator {
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");

    public static boolean isCepValid(String cep) {
        return matchesPattern(cep);
    }

    private static boolean matchesPattern(String cep) {
        return CEP_PATTERN.matcher(cep).matches();
    }
}
