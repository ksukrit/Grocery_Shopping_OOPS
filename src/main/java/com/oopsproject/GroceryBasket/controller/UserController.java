package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.ShippingAddress;
import com.oopsproject.GroceryBasket.model.User;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @RequestMapping("/customer/list")
    public List<Customer> getCustomerList(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/register")
    public void addCustomer(@RequestBody MultiValueMap<String, String> userFormData){
        Customer c = new Customer();
        User u = new User();
        u.setEmailId(userFormData.getFirst("username"));
        u.setPassword(userFormData.getFirst("password"));
        c.setUsers(u);
        c.setFirstName(userFormData.getFirst("fname"));
        c.setLastName(userFormData.getFirst("lname"));
        c.setCustomerPhone(userFormData.getFirst("phoneNo"));

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(userFormData.getFirst("address"));
        shippingAddress.setCity(userFormData.getFirst("city"));
        shippingAddress.setZipcode(userFormData.getFirst("pincode"));
        shippingAddress.setState(userFormData.getFirst("state"));
        shippingAddress.setCountry(userFormData.getFirst("country"));

        c.setShippingAddress(shippingAddress);


        customerService.addCustomer(c);
    }


}
