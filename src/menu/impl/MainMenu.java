package menu.impl;

import configs.ApplicationContext;
import entities.Cart;
import entities.Order;
import entities.Product;
import entities.User;
import menu.Menu;
import services.OrderManagementService;
import services.UserManagementService;
import services.impl.DefaultOrderManagementService;
import services.impl.DefaultUserManagementService;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu implements Menu {

    private static final String MAIN_MENU_LOGGED_OUT = "Please enter a number to proceed\n"
            + "1. Sign Up\n"
            + "2. Sign in\n"
            + "3. Shop\n"
            + "4. Exit\n"
            + "Enter your choice (1-4):";;

    private static final String MAIN_MENU_LOGGED_IN = "Please enter a number to proceed\n"
            + "1. Sign Out\n"
            + "2. Shop\n"
            + "3. View Your Orders\n"
            + "4. Settings\n"
            + "5. Exit\n"
            + "Enter your choice (1-5):";

    private static final String MAIN_MENU_ADMIN = "Please enter a number to proceed\n"
            + "1. Sign Out\n"
            + "2. Manage Shop\n"
            + "3. View All Orders\n"
            + "4. View Orders by User"
            + "5. Manage Services\n"
            + "6. Settings\n"
            + "7. Exit\n"
            + "Enter your choice (1-7):";


    private UserManagementService userManagementService;
    private OrderManagementService orderManagementService;
    private ApplicationContext context;
    {
        userManagementService = DefaultUserManagementService.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }



    public void start(){
        printMenuHeader();
        Scanner scanner = new Scanner(System.in);
        Menu mainMenu = new MainMenu();
        while (true){
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    if (context.getLoggedInUser() == null){
                        Menu signUp = new SignUpMenu();
                        signUp.start();
                        mainMenu.start();
                    }
                    else{
                        System.out.println("Signing out...");
                        context.setLoggedInUser(null);
                        mainMenu = new MainMenu();
                        mainMenu.start();
                    }
                    break;
                case "2":
                    if (context.getLoggedInUser() == null){
                        Menu signInMenu = new SignInMenu();
                        signInMenu.start();
                    }

                    else if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        //TODO make a shop management section
                        System.out.println("Shop management");
                    }
                    else{
                        Menu shopMenu = new ShopMenu();
                        shopMenu.start();

                    }
                    mainMenu.start();
                case "3":
                    if(context.getLoggedInUser() == null){
                        Menu shopMenu = new ShopMenu();
                        shopMenu.start();
                    }
                    else if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        ArrayList< Order >allOrders = orderManagementService.getOrders();

                        for (int i = 0; i<allOrders.size(); i++){
                            Order o = allOrders.get(i);
                            System.out.println("\nUser ID: " + o.getCustomerId());
                            ArrayList<Product> items = o.getProducts();
                            for(int j=0; j<items.size(); j++){
                                System.out.printf("%-6  %-20","£"+ items.get(j).getPrice(), items.get(j).getProductName());
                            }
                            System.out.println("\n");
                        }
                    }
                    else{
                        ArrayList< Order >allOrders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
                        if (allOrders.size() == 0){System.out.println("You have no orders m8");}
                        else {
                            System.out.println("Here are your orders");
                            for (int i = 0; i < allOrders.size(); i++) {
                                Order o = allOrders.get(i);
                                System.out.println("\nUser ID: " + o.getCustomerId());
                                ArrayList<Product> items = o.getProducts();
                                for (int j = 0; j < items.size(); j++) {
                                    System.out.printf("%-6  %-20", "£" + items.get(j).getPrice(), items.get(j).getProductName());
                                }
                                System.out.println("\n");
                            }
                        }
                    }
                    mainMenu.start();
                    break;
                case "4":
                    if(context.getLoggedInUser() == null){
                        System.exit(0);
                    }
                    else if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        String email = null;
                        try {
                            System.out.println("Enter email of user");
                            email = scanner.nextLine();
                            ArrayList<Order> allOrders = orderManagementService.getOrdersByUserId(userManagementService.getUserByEmail(email).getId());
                            if (allOrders.size() == 0) {
                                System.out.println("They have no orders");
                            } else {
                                System.out.println("Here are the orders");
                                for (int i = 0; i < allOrders.size(); i++) {
                                    Order o = allOrders.get(i);
                                    System.out.println("\nUser ID: " + o.getCustomerId());
                                    ArrayList<Product> items = o.getProducts();
                                    for (int j = 0; j < items.size(); j++) {
                                        System.out.printf("%-6  %-20", "£" + items.get(j).getPrice(), items.get(j).getProductName());
                                    }
                                    System.out.println("\n");
                                }
                            }
                        }
                        catch (Exception e){
                            System.out.println("Could not find user with that email");
                        }

                    }
                    else{
                        Menu settingsMenu = new SettingsMenu();
                        settingsMenu.start();
                    }
                    mainMenu.start();
                    break;
                case "5":
                    if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        System.out.println("Manage Services");
                    }
                    else if (context. getLoggedInUser() == null){
                        System.out.println("Invalid input");
                    }
                    else{
                        System.exit(0);
                    }
                    mainMenu.start();
                    break;

                case "6":
                    if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        Menu settingsMenu = new SettingsMenu();
                        settingsMenu.start();
                    }
                    else{System.out.println("Invalid input");}
                    mainMenu.start();


//                    if (context.getLoggedInUser() != null){
//                        Menu settingsMenu = new SettingsMenu();
//                        settingsMenu.start();
//                    }
//                    else{
//                        System.out.println("Please, log in or create new account to change your account settings\n");
//                        mainMenu = new MainMenu();
//                        mainMenu.start();
//                    }
                    break;
                case "7":
                    if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
                        System.exit(0);
                    }

//                    ArrayList<User> userList = userManagementService.getUsers();
//                    System.out.println("List of users:");
//                    for (int i =0; i< userList.size(); i++){
//                        userList.get(i).toString();
//                    }
//                    mainMenu = new MainMenu();
//                    mainMenu.start();
//                    break;
                case "8":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
                    mainMenu.start();
            }
        }
    }
    public void printMenuHeader(){
        System.out.println("\n*****MAIN MENU*****");
        context = ApplicationContext.getInstance();
        if(context.getLoggedInUser() == null){
            System.out.println(MAIN_MENU_LOGGED_OUT);
        }
        else if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
            System.out.println(MAIN_MENU_ADMIN);
        }
        else{
            System.out.println(MAIN_MENU_LOGGED_IN);
        }
    }

}

//   if(context.getLoggedInUser() == null){}
//   else if(context.getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){}
//   else{}
