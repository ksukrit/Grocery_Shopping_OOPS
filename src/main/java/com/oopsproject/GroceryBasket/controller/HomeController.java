package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.*;
import com.oopsproject.GroceryBasket.service.CartItemService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;


    @RequestMapping({"/","index"})
    public String home(){
        return "Hello world";
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello world";
    }

    @RequestMapping("/about")
    public String sayAbout() {
        return "aboutUs";
    }

    @RequestMapping("/user/list")
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @RequestMapping("/customer/list")
    public List<Customer> getCustomerList(){
        return customerService.getAllCustomers();
    }


    @RequestMapping("/customer/create/{name}")
    public void addCustomer(@PathVariable(value = "name") String name) {
        Customer c = new Customer();
        User u = new User();
        u.setEmailId("ksukrit2001@gmail.com");
        u.setPassword("test123");
        c.setUsers(u);
        c.setFirstName(name);
        c.setLastName(name+"LastName");
        c.setCustomerPhone("9022580264");
        ShippingAddress b = new ShippingAddress();
        b.setAddress("N");
        b.setCity("Mumbai");
        b.setState("Ind");
        b.setCountry("India");
        b.setZipcode("400058");

        c.setShippingAddress(b);
        customerService.addCustomer(c);
    }
}
