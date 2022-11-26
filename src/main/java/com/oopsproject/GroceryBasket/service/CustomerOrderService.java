package com.oopsproject.GroceryBasket.service;


import com.oopsproject.GroceryBasket.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);
    double getCustomerOrderGrandTotal(String cartId);

    CustomerOrder getCustomerOrderById(String orderId);

    List<CustomerOrder> getAllOrders();

    List<CustomerOrder> getCustomerOrderByCustomerId(String customerId);
}
