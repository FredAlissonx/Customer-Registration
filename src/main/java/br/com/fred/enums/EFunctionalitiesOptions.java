package br.com.fred.enums;


import java.util.Set;

public enum EFunctionalitiesOptions {
    REGISTER,
    CONSULT,
    CONSULT_ALL,
    REMOVE,
    EDIT,
    EXIT;
    public static EFunctionalitiesOptions getOptionFromInput(String option) {
        int enumIndex = Integer.parseInt(option) - 1;
        return EFunctionalitiesOptions.values()[enumIndex];
    }

    public static boolean isValidOption(String option) {
        return Set.of("1", "2", "3", "4", "5", "6").contains(option);
    }
}
