package entities.impl;

import entities.User;

public class DefaultUser implements User {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int id;
    private static int counter;


    public DefaultUser() {
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        counter = counter+1;
        this.id = counter;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        String message = ("ID: "+getId()
                        + "\nFirstname: "+ getFirstName()
                        + "\nLastname: " + getLastName()
                        + "\nEmail: "+getEmail()
                        +"\n"
        );
        System.out.println(message);
        return message;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getId() {

        return this.id;
    }

    void clearState() {
        // <write your code here>
    }
    public static void resetCounter(){
        counter = 0;
    }
}
