package br.com.customerregistration.services;

import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.NotificationUI;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.validations.*;

public class RegisterCustomerService {

    public static void register(ICustomerDAO iCustomerDAO) {
        String data = InputDialogUI.inputMessage( "Use the provided sample as a guide when entering customer details: First name,Last name,CPF,Phone number,City,State (UF),CEP – separate each with a comma or [1] Back to Menu", "Registering Customer");

        if (data.equals("1")) return;

        String[] dataSeparatedByComma = data.split(",");

        if (!isNumberOfDataInputValid(dataSeparatedByComma)) {
            NotificationUI.displayErrorMessage("Invalid number of information provided", "Invalid input");
            return;
        }

        if (!validateDataAndRegisterCustomer(dataSeparatedByComma, iCustomerDAO)) return;
    }

    private static boolean isNumberOfDataInputValid(String[] array) {
        return array.length == 7;
    }

    private static boolean validateDataAndRegisterCustomer(String[] data, ICustomerDAO iCustomerDAO) {
        String firstName = data[0];
        String lastName = data[1];
        String cpf = data[2];
        String phoneNumber = data[3];
        String city = data[4];
        String state = data[5];
        String cep = data[6];

        if (!validateInput(firstName, lastName, cpf, phoneNumber, city, state, cep)) {
            return false;
        }

        CustomerDTO customerDTO = createCustomerDTO(firstName, lastName, cpf, phoneNumber, city, state, cep);

        if (customerDTO == null) {
            NotificationUI.displayErrorMessage("Please provide valid information!", "Invalid input");
            return false;
        }

        if (!iCustomerDAO.register(customerDTO)) {
            NotificationUI.displayErrorMessage("Customer is already registered", "Invalid input");
        } else {
            MessageDisplayUI.displayMessage("Customer successfully registered", "Customer registered");
        }
        return true;
    }

    private static boolean validateInput(String firstName, String lastName, String cpf, String phoneNumber, String city, String state, String cep) {
        boolean isFirstNameValid = NameValidator.isNameValid(firstName);
        boolean isLastNameValid = NameValidator.isNameValid(lastName);
        boolean isCpfValid = CpfValidator.isCpfValid(cpf);
        boolean isPhoneNumberValid = PhoneValidator.isPhoneNumberValid(phoneNumber);
        boolean isAddressValid = AddressValidator.isAddressValid(city, state);
        boolean isCepValid = CepValidator.isCepValid(cep);

        if (!isFirstNameValid || !isLastNameValid) {
            NotificationUI.displayErrorMessage("Invalid name, please try again!", "Invalid name");
            return false;
        } else if (!isCpfValid) {
            NotificationUI.displayErrorMessage("Invalid CPF, please try again!", "Invalid CPF");
            return false;
        } else if (!isPhoneNumberValid) {
            NotificationUI.displayErrorMessage("Invalid phone number, please try again!", "Invalid phone number");
            return false;
        } else if (!isAddressValid) {
            NotificationUI.displayErrorMessage("Invalid address, please try again!", "Invalid address");
            return false;
        }
        else if (!isCepValid) {
            NotificationUI.displayErrorMessage("Invalid CEP, please try again!", "Invalid CEP");
            return false;
        }
        return true;
    }

    private static CustomerDTO createCustomerDTO(String firstName, String lastName, String cpf, String phoneNumber, String city, String state, String cep) {
        try {
            return new CustomerDTO(firstName, lastName, cpf, phoneNumber, city, state, cep);
        } catch (Exception e) {
            NotificationUI.displayErrorMessage("Please provide valid information!", "Invalid input");
            return null;
        }
    }
}


