package br.com.fred.ui;

import javax.swing.*;

public class ErrorsUI {
    public static void invalidMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "invalidData", JOptionPane.INFORMATION_MESSAGE);
    }
}
