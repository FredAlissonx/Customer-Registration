package br.com.customerregistration.ui;

import javax.swing.*;
public class MessageDisplayUI {

    public static void displayMessage(String message, String title) {
        showMessage(message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showMessage(String message, String title, int messageType) {
        try {
            JOptionPane.showMessageDialog(null, message, title, messageType);
        } catch (Exception e) {
            System.err.println("Error displaying output message: " + e.getMessage());
        }
    }
}
