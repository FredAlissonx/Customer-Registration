package br.com.customerregistration.enums;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum EFunctionalityOption {
    REGISTER,
    CONSULT,
    CONSULT_ALL,
    REMOVE,
    REMOVE_ALL,
    EDIT,
    EXIT;
    public static Optional<EFunctionalityOption> getOptionFromInput(String option) {
        try {
            int index = Integer.parseInt(option) - 1;
            if (index >= 0 && index < values().length) {
                return Optional.of(values()[index]);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
        }
        return Optional.empty();
    }

    public static boolean isValidOption(String option) {
        String trimedOption = option.trim();
        return Set.of("1", "2", "3", "4", "5", "6", "7").contains(trimedOption);
    }
}
