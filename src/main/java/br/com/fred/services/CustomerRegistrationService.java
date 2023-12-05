package br.com.fred.services;

import br.com.fred.dao.CustomerMapDAO;
import br.com.fred.dao.ICustomerDAO;
import br.com.fred.enums.EFunctionalitiesOptions;
import br.com.fred.ui.ErrorsUI;
import br.com.fred.ui.InputMessageUI;
import br.com.fred.ui.OutputMessageUI;
import br.com.fred.utils.ProgramExitHandler;


public class CustomerRegistrationService {
    public static void run() {

        ICustomerDAO iCustomerDAO = new CustomerMapDAO();

        // show an interface to option
        String option = InputMessageUI.inputMessage("Type 1 to register, 2 to consult, 3 to consult all, 4 to remove, 5 to edit or 6 to exit");

        while (!EFunctionalitiesOptions.isValidOption(option)) {
            option = InputMessageUI.inputMessage("Invalid option, please type 1 to register, 2 to consult, 3 to consult all, 4 to remove, 5 to edit or 6 to exit");
        }

        while (EFunctionalitiesOptions.isValidOption(option)) {

            EFunctionalitiesOptions functionalitiesOption = EFunctionalitiesOptions.getOptionFromInput(option);

            if (functionalitiesOption == EFunctionalitiesOptions.EXIT) ProgramExitHandler.exitProgram();

            String data;
            switch (functionalitiesOption) {
                case REGISTER -> {
                    data = InputMessageUI.inputMessage("Enter the customer data separated by a comma, as in the example: First name, Last name, CPF, Phone number, City, State and CEP");
                    RegisterService.register(iCustomerDAO, data);
                }
                case CONSULT -> {
                    data = InputMessageUI.inputMessage("Type the CPF: ");
                    ConsultService.consult(iCustomerDAO, data);
                }
                case CONSULT_ALL -> {
                    ConsultAllService.searchAll(iCustomerDAO.searchAll());
                }
                case REMOVE -> {
                    data = InputMessageUI.inputMessage("Type the CPF: ");
                    RemoveService.remove(iCustomerDAO, data);
                }
                case EDIT -> {
                    String customerCpf = InputMessageUI.inputMessage("Type customer cpf: ");
                    if (!EditService.customerValidToEdit(iCustomerDAO, customerCpf)){
                        OutputMessageUI.outputMessage("Customer not found!");
                        break;
                    }
                    data = InputMessageUI.inputMessage("Chose one to edit: 1 - First name, 2 - Last name, 3 - Phone number, 4 - City, 5 - State, 6 - CEP, 7 - Edit all");
                    while (!EditService.isValidOption(data)){
                        OutputMessageUI.outputMessage("Invalid option!");
                        data = InputMessageUI.inputMessage("Chose one to edit: 1 - First name, 2 - Last name, 3 - Phone number, 4 - City, 5 - State, 6 - CEP, 7 - Edit all");
                    }
                    EditService.edit(iCustomerDAO, data, customerCpf);
                }
                case EXIT -> ProgramExitHandler.exitProgram();
            }

            option = InputMessageUI.inputMessage("Type 1 to register, 2 to consult, 3 to consult all, 4 to remove, 5 to edit or 6 to exit");
        }
    }
}
