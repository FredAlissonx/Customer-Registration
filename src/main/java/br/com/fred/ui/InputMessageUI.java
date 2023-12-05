package br.com.fred.ui;

import javax.swing.*;

public class InputMessageUI {
    public static String inputMessage(String message){
        return JOptionPane.showInputDialog(null,
                message,"", JOptionPane.INFORMATION_MESSAGE);
    }

}
