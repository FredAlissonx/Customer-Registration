package br.com.customerregistration.services;

import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.ui.MessageDisplayUI;
import br.com.customerregistration.ui.NotificationUI;

public class RemoveAllCustomersService {
    private static final String CONFIRMATION_YES = "y";
    private static final String CONFIRMATION_NO = "n";

    public static void removeAllCustomers(ICustomerDAO customerMapDAO) {
        if (customerMapDAO.getAllCustomers().isEmpty()){
            NotificationUI.displayErrorMessage("Are not customers registered!", "There's no customer");
            return;
        }
        String data = InputDialogUI.inputMessage("Do you really want to remove all customers? [y] or [n]", "Confirmation");

        if (CONFIRMATION_NO.equalsIgnoreCase(data))
            MessageDisplayUI.displayMessage("Okay, coming back to Menu", "Back to menu");
        else if (CONFIRMATION_YES.equalsIgnoreCase(data))
            removeCustomers(customerMapDAO);
        else
            NotificationUI.displayErrorMessage("Invalid input. Please enter [ y ] or [ n ]", "Invalid option");

    }

    private static void removeCustomers(ICustomerDAO customerMapDAO) {
        if (customerMapDAO != null) {
            customerMapDAO.removeAllCustomers();
            MessageDisplayUI.displayMessage("All customers removed successfully!", "All customers removed");
        } else {
            NotificationUI.displayErrorMessage("Customer not available!", "Not available");
        }
    }
}

