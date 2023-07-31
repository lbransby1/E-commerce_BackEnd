package menu.impl;

import configs.ApplicationContext;
import entities.User;
import entities.impl.DefaultUser;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

    private UserManagementService userManagementService;
    private ApplicationContext context;
    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner scanner = new Scanner(System.in);
        boolean validCredentials = false;
        String firstName = null;
        String lastName = null;
        String email1 = null;
        String email2 = null;
        String password1 = null;
        String password2 = null;
        while (validCredentials == false) {
            System.out.println("Enter your first name, or 'back' to return to main menu:");
            firstName = scanner.nextLine();
            if (firstName.equals("back")){
                Menu mainMenu = new MainMenu();
                mainMenu.start();
            }
            System.out.println("Enter your last name:");
            lastName = scanner.nextLine();
            System.out.println("Enter Email:");
            email1 = scanner.nextLine();
            System.out.println("Re-type Email:");
            email2 = scanner.nextLine();
            System.out.println("Enter Password:");
            password1 = scanner.nextLine();
            System.out.println("Re-type Password:");
            password2 = scanner.nextLine();

            if(email1.equals(email2) && !email1.equals("")){
                if (password1.equals(password2)) {
                    System.out.println("Registering your account...");
                    User newUser = new DefaultUser(firstName, lastName, password1, email1);
                    String checkEmail = userManagementService.registerUser(newUser);
                    if (checkEmail == null){validCredentials = true;}
                }
                else{System.out.println("Passwords do not match. Please try again\n");}}
            else{System.out.println("Emails do not match or is invalid. Please try again\n");}
        }
        //once complete
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("\n*****SIGN UP MENU*****");

    }
}
