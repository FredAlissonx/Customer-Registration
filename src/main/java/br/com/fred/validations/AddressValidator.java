package br.com.fred.validations;

import br.com.fred.enums.EStatesUF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class AddressValidator {
    private static final String CSV_FILE_PATH = "src/main/java/br/com/fred/data/brazil_cities.csv";
    public static boolean isAddressValid(String city, String state) {
        File file = new File(CSV_FILE_PATH);

        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return isValidAddressInFile(br, city, state);
        } catch (IOException | IllegalArgumentException exception) {
            return false;
        }
    }

    private static boolean isValidAddressInFile(BufferedReader br, String city, String state) throws IOException {

        String line;

        while ((line = br.readLine()) != null) {
            String[] cityFields = line.split(",");
            String matchUFState = removeAccents(cityFields[0]);
            String matchCity = removeAccents(cityFields[2]);

            if (isMatchingAddress(city, state, matchCity, matchUFState)) return true;
        }
        return false;
    }

    private static boolean isMatchingAddress(String city, String state, String matchCity, String matchUFState) {
        boolean isCityMatch = matchCity.equalsIgnoreCase(city);
        boolean isUFStateMatch = matchUFState.equalsIgnoreCase(Integer.toString(EStatesUF.valueOf(state.toUpperCase()).getUF()));

        return isCityMatch && isUFStateMatch;
    }

    private static String removeAccents(String address){
        return Normalizer.normalize(address, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}

