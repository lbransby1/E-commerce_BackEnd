package menu.impl;

import configs.ApplicationContext;
import entities.Cart;
import entities.Product;
import entities.User;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu implements Menu {

    private static final String MAIN_MENU_LOGGED_OUT = "Please enter a number to proceed\n"
            + "1. Sign Up\n"
            + "2. Sign in\n"
            + "3. Shop\n"
            + "4. View cart\n"
            + "5. View orders\n"
            + "6. Settings\n"
            + "7. List Customers\n"
            + "8. Exit\n"
            + "Enter your choice (1-8):";;

    private static final String MAIN_MENU_LOGGED_IN = "Please enter a number to proceed\n"
            + "1. Sign Up\n"
            + "2. Sign Out\n"
            + "3. Shop\n"
            + "4. View cart\n"
            + "5. View orders\n"
            + "6. Settings\n"
            + "7. List Customers\n"
            + "8. Exit"
            + "Enter your choice (1-8):";

    private UserManagementService userManagementService;
    private ApplicationContext context;
    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }



    public void start(){
        printMenuHeader();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    Menu signUp = new SignUpMenu();
                    signUp.start();
                    break;
                case "2":
                    if (context.getLoggedInUser() == null){
                        Menu signInMenu = new SignInMenu();
                        signInMenu.start();
                    }
                    else{
                        System.out.println("Signing out...");
                        context.setLoggedInUser(null);
                        Menu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    break;
                case "3":
                    Menu shopMenu = new ShopMenu();
                    shopMenu.start();
                    break;
                case "4":
                    Cart cart = context.getSessionCart();
                    ArrayList<Product>products = cart.getProducts();
                    if (cart.isEmpty()){
                        System.out.println("Cart is empty\n");
                    }
                    else {
                        System.out.println("___________________________________________");
                        for (int i = 0; i<products.size(); i++){
                            Product p = products.get(i);
                            System.out.printf("| %-6s | %-20s | %-7s |%n", p.getId(), p.getProductName(), "£"+p.getPrice());
                        }
                        System.out.println("___________________________________________\n");
                        Menu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    break;
                case "5":
                    break;
                case "6":
                    if (context.getLoggedInUser() != null){
                        Menu settingsMenu = new SettingsMenu();
                        settingsMenu.start();
                    }
                    else{
                        System.out.println("Please, log in or create new account to change your account settings\n");
                        Menu mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    break;
                case "7":
                    ArrayList<User> userList = userManagementService.getUsers();
                    System.out.println("List of users:");
                    for (int i =0; i< userList.size(); i++){
                        userList.get(i).toString();
                    }
                    Menu mainMenu = new MainMenu();
                    mainMenu.start();
                    break;
                case "8":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter one of the following: 1, 2, 3, 4, 5, 6, 7 or 8.");
            }
        }
    }
    public void printMenuHeader(){
        System.out.println("\n*****MAIN MENU*****");
        context = ApplicationContext.getInstance();
        if(context.getLoggedInUser() == null){
            System.out.println(MAIN_MENU_LOGGED_OUT);
        }
        else{
            System.out.println(MAIN_MENU_LOGGED_IN);
        }
    }

}
