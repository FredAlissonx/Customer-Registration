package br.com.fred.ui;

import javax.swing.*;
public class OutputMessageUI {
    public static void outputMessage(String data){
        JOptionPane.showMessageDialog(null, data, "", JOptionPane.INFORMATION_MESSAGE);
    }
}
