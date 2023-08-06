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
        context.getSessionCart();
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
                    }
                    else{
                        System.out.println("Signing out...");
                        context.setLoggedInUser(null);
                    }
                    break;

                case "2":
                    if (context.getLoggedInUser() == null){
                        Menu signInMenu = new SignInMenu();
                        signInMenu.start();
                    }

                    else if(context.isAdmin()){
                        //TODO make a shop management section
                        System.out.println("Shop management");
                    }
                    else{
                        Menu shopMenu = new ShopMenu();
                        shopMenu.start();
                    }
                    break;

                case "3":
                    if(context.getLoggedInUser() == null){
                        Menu shopMenu = new ShopMenu();
                        shopMenu.start();
                    }
                    else if(context.isAdmin()){
                        ArrayList< Order >allOrders = orderManagementService.getOrders();

                        for (int i = 0; i<allOrders.size(); i++){
                            Order o = allOrders.get(i);
                            System.out.printf("%-10s %-4s %n","Order ID: " +i ,"User ID: " + o.getCustomerId());
                            ArrayList<Product> items = o.getProducts();
                            double price=0;
                            for(int j=0; j<items.size(); j++){
                                System.out.printf("%-4s %-6s %-20s %n",(j+1)+".","£"+items.get(j).getPrice() , items.get(j).getProductName());
                                price = price + items.get(j).getPrice();
                            }
                            System.out.println("Total: £" +price + "\n");
                        }
                    }

                    else{
                        ArrayList< Order >allOrders = orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
                        if (allOrders.size() == 0){System.out.println("You have no orders m8");}
                        else {
                            System.out.println("Here are your orders, " +context.getLoggedInUser().getFirstName()+ ":\n");

                            for (int i = 0; i < allOrders.size(); i++) {
                                Order o = allOrders.get(i);
                                ArrayList<Product> items = o.getProducts();
                                System.out.printf("%-10s %-4s %n","Order ID: " +i ,"User ID: " + o.getCustomerId());
                                double price=0;

                                for (int j = 0; j < items.size(); j++) {
                                    System.out.printf("%-4s %-6s %-20s %n",(j+1)+".","£"+items.get(j).getPrice() , items.get(j).getProductName());
                                    price = price + items.get(j).getPrice();
                                }

                                System.out.println("Total: £" +price + "\n");
                            }
                        }
                    }
                    break;

                case "4":
                    if(context.getLoggedInUser() == null){
                        System.exit(0);
                    }

                    else if(context.isAdmin()){
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
                                    ArrayList<Product> items = o.getProducts();
                                    System.out.printf("%-10s %-4s %n", "Order ID: " + i, "User ID: " + o.getCustomerId());
                                    double price = 0;

                                    for (int j = 0; j < items.size(); j++) {
                                        System.out.printf("%-4s %-6s %-20s %n", (j + 1) + ".", "£" + items.get(j).getPrice(), items.get(j).getProductName());
                                        price = price + items.get(j).getPrice();
                                    }
                                    System.out.println("Total: £" + price + "\n");
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
                    break;

                case "5":
                    if(context.isAdmin()){
                        System.out.println("Manage Services");
                    }
                    else if (context. getLoggedInUser() == null){
                        System.out.println("Invalid input");
                    }
                    else{
                        System.exit(0);
                    }
                    break;

                case "6":
                    if(context.isAdmin()){
                        Menu settingsMenu = new SettingsMenu();
                        settingsMenu.start();
                    }
                    else{System.out.println("Invalid input");}
                    break;

                case "7":
                    if(context.isAdmin()){
                        System.exit(0);
                    }
                    else{System.out.println("Invalid input");}
                    break;

                default:
                    System.out.println("Invalid input");
                    mainMenu.start();
            }
            mainMenu.start();
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
