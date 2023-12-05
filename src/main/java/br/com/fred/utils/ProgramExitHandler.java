package br.com.fred.utils;

import javax.swing.*;

public class ProgramExitHandler {
    public static void exitProgram(){
        showExitMessage();
        System.exit(0); // closes java program
    }
    public static void showExitMessage(){
        JOptionPane.showMessageDialog(null,
                "Thanks for using our system!",
                "exitingprogram", JOptionPane.INFORMATION_MESSAGE);
    }
}
