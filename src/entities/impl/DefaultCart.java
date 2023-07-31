package entities.impl;

import entities.Cart;
import entities.Product;

import java.util.ArrayList;

public class DefaultCart implements Cart {

    private ArrayList<Product> Products;

    public DefaultCart(){
        Products = new ArrayList<>();
    }

    public boolean isEmpty(){
        if(getProducts().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void addProduct(Product product) {
        getProducts().add(product);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.Products;
    }

    @Override
    public void clear() {
        getProducts().clear();
    }

}
