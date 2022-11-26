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


    @RequestMapping("/login")
    public ModelAndView loginPage(){
        System.out.println("Login page ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

}
