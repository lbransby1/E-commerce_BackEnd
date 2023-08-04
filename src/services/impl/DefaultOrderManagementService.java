package services.impl;

import entities.Order;
import entities.Product;
import services.OrderManagementService;
import services.ProductManagementService;

import java.util.ArrayList;

public class DefaultOrderManagementService implements OrderManagementService {

    private ArrayList<Order> Orders;

    private static DefaultOrderManagementService instance;
    {
        Orders = new ArrayList<Order>();
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        Orders.add(order);
    }

    @Override
    public ArrayList<Order> getOrdersByUserId(int userId) {
        ArrayList<Order> ordersWithId = new ArrayList<>();
        for (int i =0; i< Orders.size(); i++){
            if(Orders.get(i).getCustomerId() == userId){
                ordersWithId.add(Orders.get(i));
            }
        }
        return ordersWithId;
    }

    @Override
    public ArrayList<Order> getOrders() {
        return Orders;
    }
}
