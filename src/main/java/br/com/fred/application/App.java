package br.com.fred.application;

import br.com.fred.service.CustomerRegistrationService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // dependency injection
        try {
            CustomerRegistrationService.run();
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, "Hello", "startError", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
