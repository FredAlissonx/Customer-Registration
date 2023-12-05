package br.com.fred.validations;

import java.util.regex.Pattern;

public class CepValidator {
    public static boolean isCepValid(String cep){
        return Pattern.compile("\\d{8}").matcher(cep).matches();
    }
}
