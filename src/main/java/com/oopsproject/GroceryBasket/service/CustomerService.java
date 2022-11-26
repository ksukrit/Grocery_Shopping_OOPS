package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Customer;

public interface CustomerService {

    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerByemailId(String emailId);

}