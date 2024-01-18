package br.com.customerregistration.enums;

import java.util.Arrays;
import java.util.Set;

public enum EEditOption {
    FIRST_NAME,
    LAST_NAME,
    PHONE_NUMBER,
    ADDRESS,
    CEP,
    ALL,
    MENU;

    public static EEditOption getOptionFromInput(String option) {
        try {
            int index = Integer.parseInt(option) - 1;
            if (index >= 0 && index < values().length) {
                return values()[index];
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
        }
        return MENU;
    }
}
