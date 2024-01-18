package br.com.customerregistration.ui;

import javax.swing.*;


public class InputDialogUI {

    public static String inputMessage(String message, String title) {
        try {
            return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error displaying input dialog: " + e.getMessage());
            return null;
        }
    }
}

