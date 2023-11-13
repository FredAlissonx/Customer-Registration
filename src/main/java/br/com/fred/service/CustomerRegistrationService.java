package br.com.fred.service;

import br.com.fred.dao.CustomerMapDAO;
import br.com.fred.dao.ICustomerDAO;
import br.com.fred.dto.CustomerDTO;
import br.com.fred.validations.AddressValidator;
import br.com.fred.validations.CpfValidator;
import br.com.fred.validations.NameValidator;
import br.com.fred.validations.PhoneValidator;

import javax.swing.*;
import java.util.Set;


public class CustomerRegistrationService{

    private static ICustomerDAO iCustomerDAO;

    public static void run() {

        iCustomerDAO = new CustomerMapDAO();

        // show an interface to option
        String option = UserInterfaceServices.inputMessage("Type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit");

        while (!isValidOption(option)) {

            // first I will check if the customer want to exit
            if (isExitOption(option)) {
                exit();
            }

            // to show the input interface to customer
            option = UserInterfaceServices.inputMessage("Invalid option, please type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit");
        }

        while (isValidOption(option)) {

            if (isExitOption(option)) {
                exit();
            } else if (isRegisterOption(option)) {
                //first name, last name, cpf, phone number, city, state, cep
                String data = JOptionPane.showInputDialog(null,
                        "Enter the customer data separated by a comma, as in the example: First name, Last name, CPF, Phone number, City, State and CEP",
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

        String firstName = dataSeparatedByComma[0];
        String lastName = dataSeparatedByComma[1];
        String cpf = dataSeparatedByComma[2];
        String phoneNumber = dataSeparatedByComma[3];
        String city = dataSeparatedByComma[4];
        String state = dataSeparatedByComma[5];
        String cep = dataSeparatedByComma[6];

        // validate customerDTO name
        if (!NameValidator.isNameValid(firstName, lastName)) {
            UserInterfaceServices.invalidMessage("Invalid name, please try again!");
            return;
        }

        //validate CPF(personal identifier in Brazil)
        // cpf generator to test: https://www.4devs.com.br/gerador_de_cpf
        else if (!CpfValidator.isCpfValid(cpf)) {
            UserInterfaceServices.invalidMessage("Invalid CPF, please try again!");
            return;
        }
        // validate phone number
        // phone number generator to test: https://devtools.com.br/gerador-numero-telefone/
        else if (!PhoneValidator.isPhoneNumberValid(phoneNumber)) {
            UserInterfaceServices.invalidMessage("Invalid phone number, please try again!");
            return;
        }
        else if(!AddressValidator.isAddressValid(city, state)){
            UserInterfaceServices.invalidMessage("Invalid address, please try again!");
            return;
        }

        CustomerDTO customerDTO;

        try {
            customerDTO = new CustomerDTO(firstName, lastName, cpf, phoneNumber, city, state, cep);
        } catch (Exception exception) {
            UserInterfaceServices.invalidMessage("Some data are invalid!");
            return;
        }

        boolean isRegistered = iCustomerDAO.register(customerDTO);

        if (!isRegistered) {
            JOptionPane.showMessageDialog(null, "CustomerDTO is already registered", "Registered", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Successfully registered customerDTO", "Successfully", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void consult(String data) {

        CustomerDTO customerDTO = iCustomerDAO.consult(Long.parseLong(data));

        if (customerDTO != null) {
            JOptionPane.showMessageDialog(null, "CustomerDTO founded: " + customerDTO, "Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "CustomerDTO not founded!", "Not founded", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void remove(String cpf) {
        CustomerDTO customerDTO = iCustomerDAO.consult(Long.parseLong(cpf));
        if (customerDTO != null) {

            iCustomerDAO.remove(Long.parseLong(cpf));

            JOptionPane.showMessageDialog(null, customerDTO.getFirstName() + " removed with success", "removed", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, cpf + " does not exist in our register", "Notremoved", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //TODO: Edit option
    public static void edit(CustomerDTO customerDTO) {
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
