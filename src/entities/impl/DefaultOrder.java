package entities.impl;

import entities.Order;
import entities.Product;

import java.util.ArrayList;

public class DefaultOrder implements Order {
    private String creditCardNumber;
    private int id;
    private ArrayList<Product> products;

    public DefaultOrder(){}


    @Override
    public boolean isCreditCardNumberValid(String userInput) {
        //TODO fix this lmao
        return true;
    }

    @Override
    public void setCreditCardNumber(String userInput) {
        creditCardNumber = userInput;
    }

    @Override
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void setCustomerId(int customerId) {
        id = customerId;
    }

    @Override
    public int getCustomerId() {
        return id;
    }
}
