package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Customer;

public interface CustomerDao {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerByemailId(String emailId);

    void updateCustomer(Customer customer);
}
