package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.CustomerOrder;
import com.oopsproject.GroceryBasket.model.Product;
import com.oopsproject.GroceryBasket.service.CustomerOrderService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportsController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/reports/getStock/{productId}")
    public Product getProductStock(@PathVariable("productId") String productId){
        return productService.getProductById(productId);
    }

    @RequestMapping("/reports/admin/getStockByName/{name}")
    public List<Product> getProductsStock(@PathVariable("name") String productName){
        return productService.getProductsByName(productName);
    }

    @RequestMapping("/reports/admin/customerOrders")
    public List<CustomerOrder> getAllCustomerOrder(){
        return customerOrderService.getAllOrders();
    }

    @RequestMapping("/reports/user/userOrders")
    public List<CustomerOrder> getCustomerOrders(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) return new ArrayList<>();
        Customer customer = customerService.getCustomerByemailId(user.getUsername());
        return customerOrderService.getCustomerOrderByCustomerId(customer.getCustomerId());
    }

    @RequestMapping("/reports/admin/customerOrderOnData")
    public List<CustomerOrder> getOrdersOnDate( @RequestParam(required = true, value = "fromTime") Long fromTime,@RequestParam(required = true, value = "toTime") Long toTime ){
       System.out.println(fromTime + " " + toTime);
        return customerOrderService.getCustomerOrderInRange(fromTime,toTime);
    }


}
