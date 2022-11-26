package com.oopsproject.GroceryBasket.dao;


import com.oopsproject.GroceryBasket.model.CustomerOrder;

public interface CustomerOrderDao {

    void addCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder getCustomerOrderById(String orderId);
}
