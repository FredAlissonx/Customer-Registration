package br.com.fred.service;

import br.com.fred.auth.IAuthManager;
import br.com.fred.dao.CustomerMapDAO;
import br.com.fred.dao.ICustomerDAO;
import br.com.fred.domain.Customer;

import javax.swing.*;
import java.util.Set;


public class CustomerRegistrationService implements IAuthManager {

    private static ICustomerDAO iCustomerDAO;

    public static void run() {

        iCustomerDAO = new CustomerMapDAO();

        // show an interface to option
        String option = UserInterfaceServices.displayInputMessage();

        while (!isValidOption(option)) {

            // first I will check if the customer want to exit
            if (isExitOption(option)) {
                exit();
            }

            // to show the input interface to customer
            option = JOptionPane.showInputDialog(null,
                    "Invalid option, please type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isValidOption(option)) {

            if (isExitOption(option)) {
                exit();
            } else if (isRegisterOption(option)) {
                String data = JOptionPane.showInputDialog(null,
                        "Enter the customer data separated by a comma, as in the example: Name, CPF, Phone number, Address, Number, City and State",
                        JOptionPane.INFORMATION_MESSAGE);
                register(data);
            } else if (isConsult(option)) {
                String data = JOptionPane.showInputDialog(null,
                        "Type the CPF: ", "Consulting",
                        JOptionPane.INFORMATION_MESSAGE);
                consult(data);
            } else if (isRemove(option)) {
                String data = JOptionPane.showInputDialog(null,
                        "Type the CPF: ", "Consulting",
                        JOptionPane.INFORMATION_MESSAGE);

                remove(data);
            }

            option = JOptionPane.showInputDialog(null,
                    "Type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void register(String data) {
        String[] dataSeparatedByComma = data.split(",");

        // remove all spaces
        for (int i = 0; i < dataSeparatedByComma.length; i++) {
            dataSeparatedByComma[i] = dataSeparatedByComma[i].trim();
        }

        IAuthManager customerValidation = new CustomerRegistrationService();

        String name = dataSeparatedByComma[0];
        String cpf = dataSeparatedByComma[1];
        String phoneNumber = dataSeparatedByComma[2];
        String address = dataSeparatedByComma[3];
        String number = dataSeparatedByComma[4];
        String city = dataSeparatedByComma[5];
        String state = dataSeparatedByComma[6];

        // validate customer name
        if (!customerValidation.isCustomerNameValid(name)) {
            UserInterfaceServices.displayInvalidMessage("Invalid name, please try again!");
            return;
        }

        //validate CPF(personal identifier in Brazil)
        // cpf generator to test: 1
        else if (!customerValidation.isCpfValid(cpf)) {
            UserInterfaceServices.displayInvalidMessage("Invalid CPF, please try again!");
            return;
        }
        // validate phone number
        // phone number generator to test: https://devtools.com.br/gerador-numero-telefone/
        else if (!customerValidation.isPhoneNumberValid(phoneNumber)) {
            UserInterfaceServices.displayInvalidMessage("Invalid phone number, please try again!");
            return;
        }

        Customer customer;

        try {
            customer = new Customer(name, cpf, phoneNumber, address, number, city, state);
        } catch (Exception exception) {
            UserInterfaceServices.displayInvalidMessage("Some data are invalid!");
            return;
        }

        boolean isRegistered = iCustomerDAO.register(customer);

        if (!isRegistered) {
            JOptionPane.showMessageDialog(null, "Customer is already registered", "Registered", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Successfully registered customer", "Successfully", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void consult(String data) {

        Customer customer = iCustomerDAO.consult(Long.parseLong(data));

        if (customer != null) {
            JOptionPane.showMessageDialog(null, "Customer founded: " + customer.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Customer not founded!", "Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void remove(String cpf) {
        Customer customer = iCustomerDAO.consult(Long.parseLong(cpf));
        if (customer != null) {

            iCustomerDAO.remove(Long.parseLong(cpf));

            JOptionPane.showMessageDialog(null, customer.getName() + " removed with success", "removed", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, cpf + " does not exist in our register", "Notremoved", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //TODO: Edit option
    public static void edit(Customer customer) {
    }

    private static void exit() {
        JOptionPane.showMessageDialog(null,
                "Thanks for using our system!",
                "Exit", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // closes java program
    }

    private static boolean isValidOption(String option) {
        return Set.of("1", "2", "3", "4", "5").contains(option);
    }

    public static boolean isRegisterOption(String option) {
        return "1".equals(option);
    }

    private static boolean isConsult(String option) {
        return "2".equals(option);
    }

    public static boolean isRemove(String option) {
        return "3".equals(option);
    }

    public static boolean isEdit(String option) {
        return "4".equals(option);
    }

    public static boolean isExitOption(String option) {
        return "5".equals(option);
    }
}
