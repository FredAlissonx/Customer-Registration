package br.com.fred.services;

import br.com.fred.dao.ICustomerDAO;
import br.com.fred.dto.CustomerDTO;

import javax.swing.*;

public class RemoveService {
    public static void remove(ICustomerDAO iCustomerDAO, String cpf) {
        CustomerDTO customerDTO = iCustomerDAO.consult(cpf);
        if (customerDTO != null) {
            iCustomerDAO.remove(cpf);
            JOptionPane.showMessageDialog(null, customerDTO.getFirstName() + " removed with success", "removed", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, cpf + " does not exist in our register", "Notremoved", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
