package br.com.fred.enums;

import java.util.Set;

public enum EEditOptions {
    FIRST_NAME,
    LAST_NAME,
    PHONE_NUMBER,
    CITY,
    STATE,
    CEP,
    ALL;
    public static EEditOptions getOptionFromInput(String option){
        int index = Integer.parseInt(option) - 1;
        return EEditOptions.values()[index];
    }
    public static boolean isValidOption(String option) {
        return Set.of("1", "2", "3", "4", "5", "6", "7").contains(option);
    }
}
