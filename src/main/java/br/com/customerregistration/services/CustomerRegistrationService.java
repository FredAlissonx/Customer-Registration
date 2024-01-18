package br.com.customerregistration.services;

import br.com.customerregistration.dao.CustomerMapDAO;
import br.com.customerregistration.dao.ICustomerDAO;
import br.com.customerregistration.enums.EFunctionalityOption;
import br.com.customerregistration.ui.InputDialogUI;
import br.com.customerregistration.utils.ExitHandler;

import java.util.Optional;


public class CustomerRegistrationService {
    private static final String MENU_PROMPT = "Type 1 to register, 2 to consult, 3 to consult all, 4 to remove, 5 to remove all, 6 to edit or 7 to exit";

    public static void run() {
        ICustomerDAO iCustomerDAO = new CustomerMapDAO();
        String option = showMenuAndGetOption();

        while (EFunctionalityOption.isValidOption(option)) {
            Optional<EFunctionalityOption> functionalitiesOption = EFunctionalityOption.getOptionFromInput(option);

            functionalitiesOption.ifPresent(eFunctionalityOption -> handleFunctionality(iCustomerDAO, eFunctionalityOption));

            option = showMenuAndGetOption();
        }
    }

    private static String showMenuAndGetOption() {
        String option = InputDialogUI.inputMessage(MENU_PROMPT, "Menu");
        while (!EFunctionalityOption.isValidOption(option)) {
            option = InputDialogUI.inputMessage("Invalid option! " + MENU_PROMPT, "Menu");
        }
        return option.trim();
    }

    private static void handleFunctionality(ICustomerDAO iCustomerDAO, EFunctionalityOption functionality) {
        switch (functionality) {
            case REGISTER -> RegisterCustomerService.register(iCustomerDAO);
            case CONSULT -> ConsultCustomerService.consult(iCustomerDAO);
            case CONSULT_ALL -> ConsultAllCustomersServices.displayAllCustomers(iCustomerDAO.getAllCustomers());
            case REMOVE -> RemoveCustomerService.remove(iCustomerDAO);
            case REMOVE_ALL -> RemoveAllCustomersService.removeAllCustomers(iCustomerDAO);
            case EDIT -> EditCustomerService.edit(iCustomerDAO);
            case EXIT -> ExitHandler.exitProgramWithMessage();
        }
    }
}
