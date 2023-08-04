package services;

import entities.Order;
import entities.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface OrderManagementService {

    void addOrder(Order order);

    ArrayList<Order> getOrdersByUserId(int userId);

    ArrayList<Order> getOrders();


}
