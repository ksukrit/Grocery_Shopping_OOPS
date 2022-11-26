package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;
import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.CustomerOrder;
import com.oopsproject.GroceryBasket.service.CartItemService;
import com.oopsproject.GroceryBasket.service.CartService;
import com.oopsproject.GroceryBasket.service.CustomerOrderService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/buyNow/{productId}")
    public String buyNow(@PathVariable("productId") String productId){
        CustomerOrder customerOrder = new CustomerOrder();
        Cart c = new Cart();
        CartItem ci = new CartItem();
        ci.setCart(c);
//        ci.setProduct();
//        c.setCartItem();
        // Make new cart with just single product
        // TODO: Work on this
        return "WIP";
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
        // Update CartId for customerOrder - set CartId

        customerOrder.setDeliveryDate(System.currentTimeMillis()+cart.getCartItem().get(0).getProduct().getDeliveryDate()*1000L);
        customerOrder.setCart(cart);
        cart.setTotalPrice(getCartTotal(cart));
        Customer customer = cart.getCustomer();
        if(cart.getTotalPrice() > customer.getWalletBalance()){
            return "Insufficient Balance";
        }
        customerOrder.setCustomer(customer);
        customerOrder.setShippingAddress(customer.getShippingAddress());


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

    @RequestMapping("/order/user/{userId}")
    public List<CustomerOrder> getOrdersByUserId(@PathVariable("userId") String userId){
        return new ArrayList<>();
    }
}