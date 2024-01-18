package br.com.customerregistration.ui;

import javax.swing.*;

public class NotificationUI {
    public static void displayErrorMessage(String message, String title) {
        showMessage(message, title, JOptionPane.ERROR_MESSAGE);
    }
    private static void showMessage(String message, String title, int messageType) {
        try {
            JOptionPane.showMessageDialog(null, message, title, messageType);
        } catch (Exception e) {
            System.out.println("Error displaying message dialog: " + e.getMessage());
        }
    }
}
