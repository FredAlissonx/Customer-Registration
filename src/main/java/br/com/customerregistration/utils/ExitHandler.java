package br.com.customerregistration.utils;

import javax.swing.*;

public class ExitHandler {
    public static void exitProgramWithMessage(){
        displayExitMessage();
        exitProgram();
    }
    private static void exitProgram(){
        System.exit(0);
    }
    private static void displayExitMessage(){
        JOptionPane.showMessageDialog(null,
                "Thanks for using our system!",
                "ExitingProgram", JOptionPane.INFORMATION_MESSAGE);
    }
}
