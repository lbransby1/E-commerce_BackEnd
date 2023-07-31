package menu.impl;

import configs.ApplicationContext;
import entities.Cart;
import entities.Product;
import entities.impl.DefaultProduct;
import menu.Menu;
import services.ProductManagementService;
import services.UserManagementService;
import services.impl.DefaultProductManagementService;
import services.impl.DefaultUserManagementService;

import java.util.ArrayList;
import java.util.Scanner;

public class ShopMenu implements Menu{

    private ProductManagementService ProductManagementService;
    private UserManagementService UserManagementService;
    private ApplicationContext context;
    {
        UserManagementService = DefaultUserManagementService.getInstance();
        ProductManagementService = DefaultProductManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printMenuHeader();
        Scanner scanner = new Scanner(System.in);

        Cart cart = context.getSessionCart();

        while (true){
            System.out.println("1. Add an item to the cart\n" +
                    "2. View cart\n" +
                    "3. Clear cart\n" +
                    "4. Return to main menu\n" +
                    "Please select an option (1-4):");
            String input = scanner.nextLine();

            switch (input){
                case "1":
                    boolean addItemSuccess = false;

                    while (addItemSuccess == false) {
                        System.out.println("Enter the ID of the product you want to order:");
                        String id = scanner.nextLine();
                        ArrayList<Product> products = ProductManagementService.getProducts();
                        for(int i=0; i<products.size(); i++){
                            if (id.equals(String.valueOf(products.get(i).getId()))){
                                Product product = products.get(i);
                                cart.addProduct(products.get(i));
                                addItemSuccess = true;
                                System.out.println("Successfully added '" + product.getProductName() + "' to your cart\n");
                                break;
                            }
                        }
                        if (addItemSuccess == false) {
                            System.out.println("No product ID match\n");
                        }
                    }
                    break;
                case "2":
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
                    }
                    break;
                case "3":
                    cart.clear();
                    System.out.println("Your cart is now cleared\n");
                    break;
                case "4":
                    Menu mainMenu = new MainMenu();
                    mainMenu.start();
                default:
                    System.out.println("Please select a valid number");
                    break;
            }
        }

    }

    @Override
    public void printMenuHeader() {
        System.out.println("*****SHOP*****");
        //addItems();
        ArrayList<Product>Products = ProductManagementService.getProducts();
        System.out.println("_____________________________________________________________");
        System.out.printf("| %-6s | %-20s | %-7s | %-15s |%n", "ID", "NAME", "PRICE", "CATEGORY");
        System.out.println("_____________________________________________________________");
        for(int i = 0; i< Products.size(); i++){
            Product p =  Products.get(i);
            System.out.printf("| %-6s | %-20s | %-7s | %-15s |%n", p.getId(), p.getProductName(), "£"+p.getPrice(), p.getCategoryName());
        }
        System.out.println("_____________________________________________________________");
    }

    public void addItems(){
        ProductManagementService.addProduct(new DefaultProduct(84734 , "T-shirt", "clothing", 15.99));
        ProductManagementService.addProduct(new DefaultProduct(84454 , "Hoodie", "clothing", 25.99));
    }
}
