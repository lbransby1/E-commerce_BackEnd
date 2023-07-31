package menu.impl;

import com.sun.tools.javac.Main;
import configs.ApplicationContext;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SettingsMenu implements Menu {

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
        String choice = scanner.nextLine();
        boolean valid = false;
        switch (choice){
            case "1":
                while (valid == false) {
                    System.out.println("Enter new email address:");
                    String email1 = scanner.nextLine();
                    System.out.println("Re-enter the new email address:");
                    String email2 = scanner.nextLine();
                    if(email1.equals(email2)) {
                        valid = true;
                        context.getLoggedInUser().setEmail(email1);
                        System.out.println("Email address has been changed");
                        Menu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    else{
                        System.out.println("The emails don't match");
                    }
                }
                break;
            case"2":
                while (valid == false) {
                    System.out.println("Enter new password:");
                    String pw1 = scanner.nextLine();
                    System.out.println("Re-enter the new password:");
                    String pw2 = scanner.nextLine();
                    if(pw1.equals(pw2)) {
                        valid = true;
                        context.getLoggedInUser().setPassword(pw1);
                        System.out.println("Password has been changed");
                        Menu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    else{
                        System.out.println("The passwords don't match");
                    }
                }
                break;
            case "3":
                Menu mainMenu = new MainMenu();
                mainMenu.start();
                break;

            default:
                System.out.println("That is not a valid input dude. type 1, 2, or 3");
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("*****SETTINGS MENU*****");
        System.out.println("Please enter a number to proceed\n" +
                "1. Change email\n" +
                "2. Change password\n" +
                "3. Back to main menu");
    }
}
