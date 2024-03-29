package br.com.customerregistration.validations;

import br.com.customerregistration.enums.EStateUF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class AddressValidator {
    private static final String CSV_FILE_PATH = "src/main/java/br/com/customerregistration/data/brazil_cities.csv";
    public static boolean isAddressValid(String city, String state) {
        File file = new File(CSV_FILE_PATH);

        String trimCity = city.trim();
        String trimState = state.trim();

        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return isValidAddressInFile(br, removeAccents(trimCity), removeAccents(trimState));
        } catch (IOException | IllegalArgumentException exception) {
            return false;
        }
    }

    private static boolean isValidAddressInFile(BufferedReader br, String city, String state) throws IOException {

        String line;

        while ((line = br.readLine()) != null) {
            String[] cityFields = line.split(",");
            String matchUFState = removeAccents(cityFields[0]).trim();
            String matchCity = removeAccents(cityFields[2]).trim();

            if (isMatchingAddress(city, state, matchCity, matchUFState)) return true;
        }
        return false;
    }

    private static boolean isMatchingAddress(String city, String state, String matchCity, String matchUFState) {
        boolean isCityMatch = matchCity.equalsIgnoreCase(city);
        boolean isUFStateMatch = matchUFState.equalsIgnoreCase(Integer.toString(EStateUF.valueOf(state.toUpperCase()).getUF()));

        return isCityMatch && isUFStateMatch;
    }

    private static String removeAccents(String address){
        return Normalizer.normalize(address, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}

