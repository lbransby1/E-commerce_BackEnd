package services;

import entities.User;

import java.util.ArrayList;

public interface UserManagementService {

    String registerUser(User user);

    ArrayList<User> getUsers();

    User getUserByEmail(String userEmail);

}
