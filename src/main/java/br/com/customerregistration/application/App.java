package br.com.customerregistration.application;

import br.com.customerregistration.services.CustomerRegistrationService;
import br.com.customerregistration.utils.ExitHandler;

public class App {
    public static void main(String[] args) {
        try {
            CustomerRegistrationService.run();
        }catch (Exception exception){
            handleException(exception);
        }
   }
    public static void handleException(Exception exception){
        ExitHandler.exitProgramWithMessage();
    }
}
