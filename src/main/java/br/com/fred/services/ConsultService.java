package br.com.fred.services;

import br.com.fred.dao.ICustomerDAO;
import br.com.fred.dto.CustomerDTO;

import javax.swing.*;

public class ConsultService {
    public static void consult(ICustomerDAO iCustomerDAO, String data) {

        CustomerDTO customerDTO = iCustomerDAO.consult(data);

        if (customerDTO != null) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder
                    .append(customerDTO.getFirstName())
                    .append(" ")
                    .append(customerDTO.getLastName())
                    .append(", CPF: ")
                    .append(customerDTO.getCpf());

            JOptionPane.showMessageDialog(null, "Customer founded: " + stringBuilder, "founded", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Customer not founded!", "not founded", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
