package br.com.fred;

import br.com.fred.dao.CustomerMapDAO;
import br.com.fred.dao.ICustomerDAO;
import br.com.fred.domain.Costumer;

import javax.swing.*;


public class App {

    private static ICustomerDAO iCustomerDAO;

    public static void main(String[] args) {

        iCustomerDAO = new CustomerMapDAO();

        // show an interface to option
        String option = JOptionPane.showInputDialog(null,
                "Type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                "Register", JOptionPane.INFORMATION_MESSAGE);

        while (!isValidOption(option)){
            if ("".equals(option)){
                exit();
            }

            option = JOptionPane.showInputDialog(null,
                    "Invalid option, please type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isValidOption(option)){
            if (isExitOption(option)){
                exit();
            }
            else if(isRegisterOption(option)){
                String data = JOptionPane.showInputDialog(null,
                        "Enter the customer data separated by a comma, as in the example: Name, CPF, Phone number, Address, Number, City and state",
                        JOptionPane.INFORMATION_MESSAGE);
                register(data);
            }
            else if(isConsult(option)){
                String data = JOptionPane.showInputDialog(null,
                        "Type the CPF: ","Consulting",
                        JOptionPane.INFORMATION_MESSAGE);
                consult(data);
            }else if (isRemove(option)){
                String data = JOptionPane.showInputDialog(null,
                        "Type the CPF: ","Consulting",
                        JOptionPane.INFORMATION_MESSAGE);

                remove(data);
            }

            option = JOptionPane.showInputDialog(null,
                    "Type 1 to register, 2 to consult, 3 to remove, 4 to edit or 5 to exit",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private static void consult(String data) {

        Costumer costumer = iCustomerDAO.consult(Long.parseLong(data));

        if (costumer != null){
            JOptionPane.showMessageDialog(null, "Costumer founded: " + costumer.toString(), "Found", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Costumer not founded!" , "Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void register(String data) {
        String[] dataSeparateByComma = data.split(",");
        Costumer costumer = new Costumer();
        if (isValidInput(dataSeparateByComma)){
            costumer = new Costumer(dataSeparateByComma[0],dataSeparateByComma[1],dataSeparateByComma[2],dataSeparateByComma[3],dataSeparateByComma[4],dataSeparateByComma[5],dataSeparateByComma[6]);
        }
        else{
            System.out.println("Invalid value");
        }

        Boolean isRegistered = iCustomerDAO.register(costumer);

        if (isRegistered){
            JOptionPane.showMessageDialog(null, "Successfully registered customer", "Successfully", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Costumer is already registered", "Registered", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public static void remove(String cpf){
        Costumer costumer = iCustomerDAO.consult(Long.parseLong(cpf));
        if (costumer != null){
            iCustomerDAO.remove(Long.parseLong(cpf));

            JOptionPane.showMessageDialog(null, costumer.getName() + " removed with success", "removed", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, cpf + " does not exist in our register", "Notremoved", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void edit(Costumer costumer){

    }

    private static void exit() {
        JOptionPane.showMessageDialog(null,
                "Thanks for using our system!",
                "Exit", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // closes java program
    }

    private static boolean isValidOption(String option) {
        if ("1".equals(option) || "2".equals(option)
        || "3".equals(option) || "4".equals(option) || "5".equals(option)){
            return true;
        }
        return false;
    }

    public static boolean isRegisterOption(String option){
        if ("1".equals(option)){
            return true;
        }
        return false;
    }
    private static boolean isConsult(String option) {
        if ("2".equals(option)){
            return true;
        }
        return false;
    }
    public static boolean isRemove(String option){
        if ("3".equals(option)){
            return true;
        }
        return false;
    }
    public static boolean isEdit(String option){
        if ("4".equals(option)){
            return true;
        }
        return false;
    }
    public static boolean isExitOption(String option){
        if ("5".equals(option)){
            return true;
        }
        return false;
    }

    public static boolean isValidInput(String[] data){

        for (String element : data){
            if (element.equals("0")){
                return false;
            }
        }
        return true;
    }
}
