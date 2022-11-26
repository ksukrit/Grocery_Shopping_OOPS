package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.*;
import com.oopsproject.GroceryBasket.service.CartItemService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

//    @RequestMapping("/login")
//    public ModelAndView login() {
//        System.out.println("Login page ");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login.html");
//        return modelAndView;
//    }
//
//    @PostMapping("/login")
//    public void loginUser(@RequestBody String fullName){
//        System.out.println(fullName);
//    }


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

    @RequestMapping("/customer/list")
    public List<Customer> getCustomerList(){
        return customerService.getAllCustomers();
    }

}
