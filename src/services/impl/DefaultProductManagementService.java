package services.impl;

import entities.Product;
import entities.impl.DefaultProduct;
import services.ProductManagementService;


import java.util.ArrayList;

public class DefaultProductManagementService implements ProductManagementService {

    private ArrayList<Product>Products;

    private static DefaultProductManagementService instance;
    {
        Products = new ArrayList<Product>();
        setProducts();
    }

    public static ProductManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultProductManagementService();
        }
        return instance;
    }
    @Override
    public ArrayList<Product> getProducts() {
        return Products;
    }


    public void addProduct(Product product) {
        Products.add(product);
    }

    public void setProducts(){
        addProduct(new DefaultProduct(84734 , "T-shirt", "clothing", 15.99));
        addProduct(new DefaultProduct(84454 , "Hoodie", "clothing", 25.99));
    }
}
