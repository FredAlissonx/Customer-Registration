package br.com.fred.application;

import br.com.fred.services.CustomerRegistrationService;
import br.com.fred.utils.ProgramExitHandler;

public class App {
    public static void main(String[] args) {
        try {
            CustomerRegistrationService.run();
        }catch (Exception exception){
            ProgramExitHandler.exitProgram();
        }
    }
}
