package services.impl;

import entities.Product;
import services.OrderManagementService;

import java.util.ArrayList;

public class DefaultOrderManagementService implements OrderManagementService {
    @Override
    public boolean isCreditCardNumberValid(String userInput) {
        return false;
    }

    @Override
    public void setCreditCardNumber(String userInput) {

    }

    @Override
    public void setProducts(ArrayList<Product> products) {

    }

    @Override
    public void setCustomerId(int customerId) {

    }

    @Override
    public int getCustomerId() {
        return 0;
    }
}
