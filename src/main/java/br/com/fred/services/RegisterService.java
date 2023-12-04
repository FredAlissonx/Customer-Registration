package br.com.fred.services;

import br.com.fred.dao.ICustomerDAO;
import br.com.fred.dto.CustomerDTO;
import br.com.fred.ui.ErrorsUI;
import br.com.fred.ui.OutputMessageUI;
import br.com.fred.validations.AddressValidator;
import br.com.fred.validations.CpfValidator;
import br.com.fred.validations.NameValidator;
import br.com.fred.validations.PhoneValidator;

import javax.swing.*;

public class RegisterService {

    public static void register(ICustomerDAO iCustomerDAO, String data) {
        String[] dataSeparatedByComma = data.split(",");

        if (!isNumberOfDataInputValid(dataSeparatedByComma)) {
            ErrorsUI.invalidMessage("Invalid number of information provided");
            return;
        }

        String firstName = dataSeparatedByComma[0];
        String lastName = dataSeparatedByComma[1];
        String cpf = dataSeparatedByComma[2];
        String phoneNumber = dataSeparatedByComma[3];
        String city = dataSeparatedByComma[4];
        String state = dataSeparatedByComma[5];
        String cep = dataSeparatedByComma[6];

        if (!validateInput(firstName, lastName, cpf, phoneNumber, city, state)) {
            return;
        }

        CustomerDTO customerDTO = createCustomerDTO(firstName, lastName, cpf, phoneNumber, city, state, cep);

        if (customerDTO == null) {
            ErrorsUI.invalidMessage("Please provide valid information!");
            return;
        }

        boolean isRegistered = iCustomerDAO.register(customerDTO);

        if (!isRegistered) {
            OutputMessageUI.outputMessage("Customer is already registered");
        } else {
            OutputMessageUI.outputMessage("Customer successfully registered");
        }
    }

    private static boolean isNumberOfDataInputValid(String[] array) {
        return array.length == 7;
    }

    private static boolean validateInput(String firstName, String lastName, String cpf, String phoneNumber, String city, String state) {
        boolean isFirstNameValid = NameValidator.isNameValid(firstName);
        boolean isLastNameValid = NameValidator.isNameValid(lastName);
        boolean isCpfValid = CpfValidator.isCpfValid(cpf);
        boolean isPhoneNumberValid = PhoneValidator.isPhoneNumberValid(phoneNumber);
        boolean isAddressValid = AddressValidator.isAddressValid(city, state);

        if (!isFirstNameValid || !isLastNameValid) {
            ErrorsUI.invalidMessage("Invalid name, please try again!");
            return false;
        } else if (!isCpfValid) {
            ErrorsUI.invalidMessage("Invalid CPF, please try again!");
            return false;
        } else if (!isPhoneNumberValid) {
            ErrorsUI.invalidMessage("Invalid phone number, please try again!");
            return false;
        } else if (!isAddressValid) {
            ErrorsUI.invalidMessage("Invalid address, please try again!");
            return false;
        }
        return true;
    }


    private static CustomerDTO createCustomerDTO(String firstName, String lastName, String cpf, String phoneNumber, String city, String state, String cep) {
        try {
            return new CustomerDTO(firstName, lastName, cpf, phoneNumber, city, state, cep);
        } catch (Exception e) {
            // Log the exception or handle it according to your application's needs
            ErrorsUI.invalidMessage("Please provide valid information! ");
            return null;
        }
    }
}

