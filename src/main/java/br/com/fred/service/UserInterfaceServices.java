package br.com.fred.service;

import javax.swing.*;

public class UserInterfaceServices {
    public static void invalidMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "invalidData", JOptionPane.INFORMATION_MESSAGE);
    }
    public static String inputMessage(String message){
        return JOptionPane.showInputDialog(null,
                message,
                "MainOptions", JOptionPane.INFORMATION_MESSAGE);
    }
}
