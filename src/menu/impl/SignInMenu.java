package menu.impl;

import com.sun.tools.javac.Main;
import configs.ApplicationContext;
import entities.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {

    private UserManagementService userManagementService;
    private ApplicationContext context;
    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        printMenuHeader();

        String email = null;
        String password = null;
        boolean validLogIn = false;

        while (validLogIn == false) {
            System.out.println("Enter email, or type 'back' to return to main menu:");
            email = scanner.nextLine();
            if (email.equals("back")){
                Menu mainMenu = new MainMenu();
                mainMenu.start();
            }
            System.out.println("Enter password:");
            password = scanner.nextLine();
            if (userManagementService.getUserByEmail(email) != null) {
                if (validLogIn = (userManagementService.getUserByEmail(email).getPassword().equals(password))) {
                    validLogIn = true;
                } else {
                    System.out.println("Sign in Failed");
                }
            }
            else{
                System.out.println("Sign in Failed");
            }
        }
        //TODO confirm the login, set it to logged in then route to main menu again
        User loginUser = userManagementService.getUserByEmail(email);
        context.setLoggedInUser(loginUser);
        System.out.println("Hello " + loginUser.getFirstName()+". You are now logged in.");
        //loginUser.toString();
        Menu mainMenu = new MainMenu();
        mainMenu.start();

    }

    @Override
    public void printMenuHeader() {
        System.out.println("*****SIGN IN MENU*****");


    }
}
