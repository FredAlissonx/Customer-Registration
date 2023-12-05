package br.com.fred.services;


import br.com.fred.dto.CustomerDTO;
import br.com.fred.ui.ErrorsUI;
import br.com.fred.ui.OutputMessageUI;

import java.util.Collection;

public class ConsultAllService {
    public static void searchAll(Collection<CustomerDTO> customers) {
        if (ConsultAllService.isConsultEmpty(customers)){
            ErrorsUI.invalidMessage("List of customers is empty!");
            return;
        }
        for (CustomerDTO customer : customers) {
            String fullName = customer.getFirstName() + " " + customer.getLastName();
            String address = customer.getCity() + ", " + customer.getState() + " CEP: " + customer.getAddress();

            String message = String.format("Name: %s, CPF: %s, Phone number: %s, Address: %s",
                    fullName, customer.getCpf(), customer.getPhoneNumber(), address);

            OutputMessageUI.outputMessage(message);
        }
    }
    public static boolean isConsultEmpty(Collection<CustomerDTO> customer){
        return customer.isEmpty();
    }
}
