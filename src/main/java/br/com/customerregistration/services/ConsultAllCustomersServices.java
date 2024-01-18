package br.com.customerregistration.services;


import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.ui.NotificationUI;

import java.util.Collection;

public class ConsultAllCustomersServices {
    public static void displayAllCustomers(Collection<CustomerDTO> customersList) {
        if (customersList.isEmpty()){
            NotificationUI.displayErrorMessage("Are not customers registered!", "There's no customer");
            return;
        }

        customersList.forEach(ConsultAllCustomersServices::displayCustomerInformation);
    }
    private static void displayCustomerInformation(CustomerDTO customer){

        String fullName = getFullName(customer);
        String address = getAddress(customer);
        String cpf = customer.getCpf().trim();
        String phoneNumber = customer.getPhoneNumber().trim();
        String title = "Customer information";

        String message = formatMessage(fullName, cpf, phoneNumber, address);
        MessageDisplayUI.displayMessage(message, title);
    }
    private static String getFullName(CustomerDTO customer) {
        return String.format("%s %s", customer.getFirstName().trim(), customer.getLastName().trim());
    }

    private static String getAddress(CustomerDTO customer) {
        return String.format("%s - %s, CEP: %s", customer.getCity().trim(), customer.getState().trim(), customer.getCep().trim());
    }

    private static String formatMessage(String fullName, String cpf, String phoneNumber, String address) {
        return String.format("Name: %s, CPF: %s, Phone number: %s, Address: %s", fullName, cpf, phoneNumber, address);
    }
}
