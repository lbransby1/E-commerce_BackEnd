package services;

import entities.Product;

import java.util.ArrayList;

public interface ProductManagementService {
    ArrayList<Product>getProducts();

    void addProduct(Product product);
}
