package services.impl;

import entities.User;
import entities.impl.DefaultUser;
import services.UserManagementService;

import java.util.ArrayList;

public class DefaultUserManagementService implements UserManagementService {
    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";

    private static final String NO_ERROR_MESSAGE = "";


    private ArrayList<User> UserList;

    private static DefaultUserManagementService instance;
    {
        UserList = new ArrayList<User>(10);
        UserList.add( new DefaultUser("Luke", "Bransby", "Nintendo15.", "luke.bransby15@gmail.com"));
    }


    private DefaultUserManagementService() {
    }

    @Override
    public String registerUser(User user) {
        // <write your code here>
        if (getUserByEmail(user.getEmail())==null){
            UserList.add(user);
            System.out.println("Successfully registered");
        }
        else{
            System.out.println(NOT_UNIQUE_EMAIL_ERROR_MESSAGE);
            return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
        }

        return null;
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public ArrayList<User> getUsers() {
        return this.UserList;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for(int i =0; i<UserList.size(); i++){
            if (userEmail!=null) {
                if (userEmail.equals(UserList.get(i).getEmail())) {
//                    SHOWS THE USER THAT MATCHES WITH THE EMAIL
//                    System.out.println("Got a match:");
//                    UserList.get(i).toString();
                    return UserList.get(i);
                }
            }
        }
        return null;
    }

    void clearServiceState() {
    }
}
