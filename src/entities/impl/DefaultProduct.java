package entities.impl;

import entities.Product;

public class DefaultProduct implements Product {
    private int id;
    private String productName;
    private String categoryName;
    private double price;

    DefaultProduct(){}

    public DefaultProduct(int id, String productName, String categoryName, double price){
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
