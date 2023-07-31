import entities.impl.DefaultUser;
import menu.Menu;
import menu.impl.MainMenu;

public class Main {
    public static void main(String[] args){
        Menu mainMenu = new MainMenu();
        mainMenu.start();
        DefaultUser.resetCounter();
    }
}
