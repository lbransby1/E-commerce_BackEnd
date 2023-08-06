package entities.impl;

import entities.Order;
import entities.Product;

import java.util.ArrayList;

public class DefaultOrder implements Order {
    private String creditCardNumber;
    private int id;
    private ArrayList<Product> products;

    public DefaultOrder(){
        products = new ArrayList<>();
    }


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
        for (int i=0; i<products.size(); i++){
            this.products.add(products.get(i));
        }
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
