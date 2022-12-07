package com.oopsproject.GroceryBasket.dao;


import com.oopsproject.GroceryBasket.model.CustomerOrder;

import java.util.List;

public interface CustomerOrderDao {

    void addCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder getCustomerOrderById(String orderId);

    List<CustomerOrder> getCustomerOrderByCustomerId(String customerId);

    List<CustomerOrder> getAllOrders();

    List<CustomerOrder> getOrderInRange(Long fromTime, Long toTime);
}
