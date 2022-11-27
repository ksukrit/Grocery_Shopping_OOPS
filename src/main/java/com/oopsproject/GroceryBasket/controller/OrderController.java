package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.*;
import com.oopsproject.GroceryBasket.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @RequestMapping("/buyNow/{productId}")
    public String buyNow(@PathVariable("productId") String productId){
        CustomerOrder customerOrder = new CustomerOrder();

        Cart c = new Cart();
        CartItem ci = new CartItem();
        ci.setCart(c);
        Product p = productService.getProductById(productId);
        ci.setProduct(p);
        ci.setQuality(1);
        ci.setPrice(p.getProductPrice());
        c.setTotalPrice(p.getProductPrice());
        c.setCartItem(Collections.singletonList(ci));

        if(p.getUnitStock() < 1){
            return "Insufficient Stock";
        }
        p.setUnitStock(p.getUnitStock()-1);

        customerOrder.setOrderDate(System.currentTimeMillis());
        customerOrder.setDeliveryDate(System.currentTimeMillis()+p.getDeliveryDate()*1000L);

        Customer customer;
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) return "";
        customer = customerService.getCustomerByemailId(user.getUsername());

        if(c.getTotalPrice() > customer.getWalletBalance()){
            return "Insufficient Balance";
        }

        c.setCustomer(customer);
        customerOrder.setCart(c);
        customerOrder.setCustomer(customer);
        customerOrder.setShippingAddress(customer.getShippingAddress());

        cartService.saveCart(c);

        cartItemService.addCartItem(ci);


        customerOrderService.addCustomerOrder(customerOrder);

        productService.editProduct(p);

        customer.setWalletBalance(customer.getWalletBalance() - c.getTotalPrice());

        customerService.updateCustomer(customer);

        return "Ordered Successfully " + customerOrder.getCustomerOrderId();
    }

    private double getCartTotal(Cart cart){
        double grandTotal=0;
        List<CartItem> cartItems = cart.getCartItem();

        for(CartItem item: cartItems){
            grandTotal += item.getPrice();
        }
        return grandTotal;
    }

    @RequestMapping("/order/{cartId}")
    public String createOrder(@PathVariable("cartId") String cartId) {

        CustomerOrder customerOrder = new CustomerOrder();

        Cart cart = cartService.getCartByCartId(cartId);

        customerOrder.setOrderDate(System.currentTimeMillis());
        customerOrder.setDeliveryDate(System.currentTimeMillis()+cart.getCartItem().get(0).getProduct().getDeliveryDate()*1000L);
        customerOrder.setCart(cart);

        Customer customer = cart.getCustomer();
        if(cart.getTotalPrice() > customer.getWalletBalance()){
            return "Insufficient Balance";
        }
        customerOrder.setCustomer(customer);
        customerOrder.setShippingAddress(customer.getShippingAddress());

        // Stock update
        List<Product> updatedProduct = new ArrayList<>();

        for(CartItem ci : cart.getCartItem()){
            if(ci.getQuality() > ci.getProduct().getUnitStock()){
                return "Insufficient product quantity";
            }else{
                ci.getProduct().setUnitStock(ci.getProduct().getUnitStock() - ci.getQuality());
                updatedProduct.add(ci.getProduct());
            }
        }

        for(Product p : updatedProduct){
            productService.editProduct(p);
        }

        customerOrderService.addCustomerOrder(customerOrder);

        Cart c = new Cart();
        customer.setCart(c);
        c.setCustomer(customer);
        customer.setWalletBalance(customer.getWalletBalance() - cart.getTotalPrice());

        customerService.updateCustomer(customer);

        return "Ordered Successfully " + customerOrder.getCustomerOrderId();
    }

    @RequestMapping("/order/details/{orderId}")
    public CustomerOrder getOrderDetails(@PathVariable("orderId") String orderId){
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        return customerOrder;
    }

    @RequestMapping("/order/user/{customerId}")
    public List<CustomerOrder> getOrdersByUserId(@PathVariable("customerId") String customerId){
        return customerOrderService.getCustomerOrderByCustomerId(customerId);
    }

    @RequestMapping("/order/list/all")
    public List<CustomerOrder> getAllOrders(){
        return customerOrderService.getAllOrders();
    }
}