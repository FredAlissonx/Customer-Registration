package br.com.customerregistration.services;

import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.ui.NotificationUI;

import javax.swing.*;

public class ConsultCustomerService {
    public static void consult(ICustomerDAO customerDAO) {

        if (customerDAO.getAllCustomers().isEmpty()) {
            NotificationUI.displayErrorMessage("Are not customers registered!", "There's no customer");
            return;
        }
        String data = InputDialogUI.inputMessage("Type the CPF: ", "Consulting").trim();

        CustomerDTO customerDTO = customerDAO.consult(data);

        if (customerDTO != null) {
            String message = generateFoundCustomerMessage(customerDTO);
            MessageDisplayUI.displayMessage(message, "Found");
        } else {
            NotificationUI.displayErrorMessage("Customer Not Found", "Not found");
        }
    }

    private static String generateFoundCustomerMessage(CustomerDTO customerDTO) {
        String fullName = getFullName(customerDTO);
        String address = getAddress(customerDTO);
        String cpf = customerDTO.getCpf();
        String phoneNumber = customerDTO.getPhoneNumber();

        return String.format(
                "Customer found: %s, CPF: %s, Phone number: %s, Address: %s",
                fullName, cpf, phoneNumber, address);
    }

    private static String getFullName(CustomerDTO customerDTO) {
        return String.format("%s %s", customerDTO.getFirstName(), customerDTO.getLastName());
    }

    private static String getAddress(CustomerDTO customerDTO) {
        return String.format("%s - %s, CEP: %s", customerDTO.getCity(), customerDTO.getState(), customerDTO.getCep());
    }

    private static void showMessageDialog(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }
}
