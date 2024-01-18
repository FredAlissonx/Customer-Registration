package br.com.customerregistration.services;

import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.dto.CustomerDTO;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.ui.NotificationUI;

public class RemoveCustomerService {
    public static void remove(ICustomerDAO iCustomerDAO) {
        if (iCustomerDAO.getAllCustomers().isEmpty()){
            NotificationUI.displayErrorMessage("Are not customers registered!", "There's no customer");
            return;
        }
        String cpf = InputDialogUI.inputMessage("Type the CPF: ", "Removing").trim();
        CustomerDTO customerDTO = iCustomerDAO.consult(cpf);
        if (customerDTO != null) {

            iCustomerDAO.remove(cpf);

            String message = customerDTO.getFirstName() + " " + customerDTO.getLastName() + " removed with success";
            MessageDisplayUI.displayMessage(message, "Remove customer");
        }
        else {
            NotificationUI.displayErrorMessage("Customer not found!", "Not found");
        }
    }
}
