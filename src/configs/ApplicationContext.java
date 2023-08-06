package configs;

import entities.Cart;
import entities.User;
import entities.impl.DefaultCart;
import menu.Menu;
import services.UserManagementService;
import services.impl.DefaultUserManagementService;

public class ApplicationContext {
    private static ApplicationContext instance;
    private User loggedInUser;
    private Menu mainMenu;
    private Cart sessionCart;
    private UserManagementService userManagementService;

    private ApplicationContext() {
        sessionCart = new DefaultCart();
        userManagementService = DefaultUserManagementService.getInstance();

    }

    public void setLoggedInUser(User user) {
        if (user == null){
            this.sessionCart.clear();
        }

        this.loggedInUser = user;
    }

    public boolean isAdmin(){
        if (getLoggedInUser() == userManagementService.getUserByEmail("luke.bransby15@gmail.com")){
            return true;
        }
        else{return false;}
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    public Menu getMainMenu() {
        return this.mainMenu;
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public Cart getSessionCart() {
        if (this.sessionCart == null) {
            this.sessionCart = new DefaultCart();
        }
        return this.sessionCart;
    }

}


