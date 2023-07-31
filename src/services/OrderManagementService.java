package services;

import entities.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface OrderManagementService {

    boolean isCreditCardNumberValid(String userInput);

    void setCreditCardNumber(String userInput);

    void setProducts(ArrayList<Product> products);

    void setCustomerId(int customerId);

    int getCustomerId();

}
