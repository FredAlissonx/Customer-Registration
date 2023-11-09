package br.com.fred.service;

import javax.swing.*;

public class UserInterfaceServices {
    public static void displayInvalidMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "invalidData", JOptionPane.INFORMATION_MESSAGE);
    }
    public static String displayInputMessage(){
        return JOptionPane.showInputDialog(null,
                "Type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                "Program", JOptionPane.INFORMATION_MESSAGE);
    }
}
