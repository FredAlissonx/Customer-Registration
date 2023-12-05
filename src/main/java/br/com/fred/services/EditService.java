package br.com.fred.services;

import br.com.fred.dao.ICustomerDAO;
import br.com.fred.dto.CustomerDTO;
import br.com.fred.enums.EEditOptions;
import br.com.fred.ui.ErrorsUI;
import br.com.fred.ui.InputMessageUI;
import br.com.fred.validations.AddressValidator;
import br.com.fred.validations.NameValidator;
import br.com.fred.validations.PhoneValidator;

import java.util.Map;
import java.util.Set;

public class EditService {
    public static void edit(ICustomerDAO customerDAO, String option, String cpf) {

        EEditOptions editOption = EEditOptions.getOptionFromInput(option);
        CustomerDTO customer = customerToEdit(customerDAO.searchAllByCpf(), cpf);
        //Chose one to edit: 1 - First name, 2 - Last name, 3 - Phone number,
        // 4 - City, 5 - State, 6 - CEP, 6 - Edit all
        switch (editOption) {
            case FIRST_NAME -> {
                if (firstNameEdit(customer) != null) customer.setFirstName(firstNameEdit(customer));
            }
            case LAST_NAME -> {
                String newLastName = lastNameEdit(customer);
                if (newLastName != null) customer.setLastName(newLastName);
            }
            case PHONE_NUMBER -> {
                if (phoneNumberEdit(customer) != null) customer.setPhoneNumber(phoneNumberEdit(customer));
            }
            case CITY -> {
                if (cityEdit(customer) != null) customer.setCity(cityEdit(customer));
            }
        }
    }
    public static boolean customerValidToEdit(ICustomerDAO iCustomerDAO, String cpf) {
        CustomerDTO customerDTO = iCustomerDAO.consult(cpf);
        return customerDTO != null;
    }

    public static CustomerDTO customerToEdit(Map<String, CustomerDTO> customersByCpf, String cpf) {
        return customersByCpf.get(cpf);
    }

    public static String firstNameEdit(CustomerDTO customer) {
        String newFirstName = InputMessageUI.inputMessage(
                "Current first name: " + customer.getFirstName() + "\nNew first name: "
        );
        if (!NameValidator.isNameValid(newFirstName)) return null;
        return newFirstName;
    }
    public static String lastNameEdit(CustomerDTO customer) {
        String newLastName = InputMessageUI.inputMessage(
                "Current last name: " + customer.getLastName() + "\nNew last name: "
        );
        if (!NameValidator.isNameValid(newLastName)) return null;

        return newLastName;
    }
    public static Long phoneNumberEdit(CustomerDTO customer){
        String newPhoneNumber = InputMessageUI.inputMessage(
                "Current number: " + customer.getPhoneNumber() + "\nNew phone number: "
        );
        if (!PhoneValidator.isPhoneNumberValid(newPhoneNumber)) return null;
        return Long.parseLong(newPhoneNumber);
    }
    public static String cityEdit(CustomerDTO customer){
        String newCity = InputMessageUI.inputMessage(
                "Current city: " + customer.getCity() +"\nNew city: "
        );

        if (!AddressValidator.isAddressValid(newCity, customer.getState())) return null;

        return newCity;
    }
    public static String stateEdit(CustomerDTO customer){
        String newState = InputMessageUI.inputMessage(
                "Current state: " + customer.getState() +"\nNew state: "
        );

        if (!AddressValidator.isAddressValid(customer.getCity(), newState)) return null;

        return newState;
    }
    public static boolean isValidOption(String option) {
        return Set.of("1", "2", "3", "4", "5", "6").contains(option);
    }
}
