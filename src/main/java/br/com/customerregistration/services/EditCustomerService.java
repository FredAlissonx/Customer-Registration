package br.com.customerregistration.services;

import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.enums.EEditOption;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.ui.NotificationUI;
import br.com.customerregistration.validations.AddressValidator;
import br.com.customerregistration.validations.CepValidator;
import br.com.customerregistration.validations.NameValidator;
import br.com.customerregistration.validations.PhoneValidator;

import java.util.Optional;
import java.util.Set;

public class EditCustomerService {
    public static void edit(ICustomerDAO customerDAO) {
        if (customerDAO.getAllCustomers().isEmpty()){
            NotificationUI.displayErrorMessage("Are not customers registered!", "There's no customer");
            return;
        }

        String customerCpf = InputDialogUI.inputMessage("Type customer cpf: ", "Customer CPF").trim();

        if (customerDAO.consult(customerCpf) == null){
            NotificationUI.displayErrorMessage("Customer cpf not found", "Cpf not found");
            return;
        }


        String option = InputDialogUI.inputMessage("Chose one to edit: 1 - First name, 2 - Last name, 3 - Phone number, 4 - Address, 5 - CEP, 6 - Back to Menu", "Editing Customer");
        while (!isValidOption(option)) {
            MessageDisplayUI.displayMessage("Invalid option, try again!", "Invalid option");
            option = InputDialogUI.inputMessage("Chose one to edit: 1 - First name, 2 - Last name, 3 - Phone number, 4 - Address, 5 - CEP, 6 - Back to Menu", "Editing Customer");
        }

        EEditOption editOption = EEditOption.getOptionFromInput(option);
        CustomerDTO customer = customerDAO.getAllByCpf().get(customerCpf);

        if (option.equals("6")) return;

        customerDAO.edit(editOption, customer);
    }

    public static String firstNameEdit(String currentFirstName) {
        String newName = InputDialogUI.inputMessage(
                "Current first name: " + currentFirstName + "\nNew first name: ", "Edit customer first name"
        );

        if (!NameValidator.isNameValid(newName))
            throw new IllegalArgumentException("Invalid first name entered.");
        if (newName.equalsIgnoreCase(currentFirstName))
            throw new IllegalArgumentException("This already is the current first name!");
        else
            MessageDisplayUI.displayMessage("First name changed with success!", "First name changed");


        return newName;
    }
    public static String lastNameEdit(String currentLastName) {
        String newLastName = InputDialogUI.inputMessage(
                "Current last name: " + currentLastName + "\nNew last name: ", "Edit customer last name"
        );

        if (!NameValidator.isNameValid(newLastName))
            throw new IllegalArgumentException("Invalid last name entered!");
        if (newLastName.equalsIgnoreCase(currentLastName))
            throw new IllegalArgumentException("This already is the current last name!");
        else
            MessageDisplayUI.displayMessage("Last name changed with success!", "Last name changed");

        return newLastName;
    }
    public static String phoneNumberEdit(String currentPhoneNumber){
        String newPhoneNumber = InputDialogUI.inputMessage(
                "Current number: " + currentPhoneNumber + "\nNew phone number: ", "Edit customer phone number"
        );

        if (!PhoneValidator.isPhoneNumberValid(newPhoneNumber))
            throw new IllegalArgumentException("Invalid phone number entered!");
        if (newPhoneNumber.equalsIgnoreCase(currentPhoneNumber))
            throw new IllegalArgumentException("This is the current phone number!");
        else
            MessageDisplayUI.displayMessage("Phone number changed with success!", "Phone number changed");
        return newPhoneNumber;
    }
    public static Optional<String[]> addressEdit(CustomerDTO customer) {
        try {
            String addressData = InputDialogUI.inputMessage(
                    "Current city: " + customer.getCity() + "\nCurrent state UF: " + customer.getState() + "\nNew city and State UF separated by comma: ", "Address"
            );

            String[] addressParts = addressData.split(",");

            if (addressParts.length != 2) {
                NotificationUI.displayErrorMessage("Invalid input format. Please provide both city and state separated by a comma.", "Invalid input");
                return Optional.empty();
            }


            String newCity = addressParts[0].trim();
            String newState = addressParts[1].trim();

            if (!AddressValidator.isAddressValid(newCity, newState))
                NotificationUI.displayErrorMessage("Invalid address entered!", "Invalid address");
            else
                MessageDisplayUI.displayMessage("Address number changed with success!", "Address changed");


            return Optional.of(new String[]{newCity, newState});
        } catch (Exception e) {
            NotificationUI.displayErrorMessage("Invalid address entered!", "Invalid address");
            return Optional.empty();
        }
    }

    public static String cepEdit(String currentCEP) {
        String newCep = InputDialogUI.inputMessage(
                "Current CEP: " + currentCEP + "\nNew CEP: ", "Edit customer address"
        );

        if (!CepValidator.isCepValid(newCep))
            throw new IllegalArgumentException("Invalid CEP entered!");
        if (newCep.equalsIgnoreCase(currentCEP))
            throw new IllegalArgumentException("This already is the current last name!");
        else
            MessageDisplayUI.displayMessage("CEP changed with success!", "CEP changed");

        return newCep;
    }

    public static boolean isValidOption(String option) {
        return Set.of("1", "2", "3", "4", "5", "6").contains(option);
    }
}
