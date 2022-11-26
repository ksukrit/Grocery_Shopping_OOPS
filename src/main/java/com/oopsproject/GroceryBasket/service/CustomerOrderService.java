package com.oopsproject.GroceryBasket.service;


import com.oopsproject.GroceryBasket.model.CustomerOrder;

public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);
    double getCustomerOrderGrandTotal(String cartId);
}
