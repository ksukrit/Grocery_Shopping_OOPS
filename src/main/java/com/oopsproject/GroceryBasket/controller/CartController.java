package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;
import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.User;
import com.oopsproject.GroceryBasket.service.CartService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/getCartById")
    public String getCartId(Model model){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailId = user.getUsername();
        Customer customer = customerService.getCustomerByemailId(emailId);
        model.addAttribute("cartId", customer.getCart().getCartId());
        return "cart";
    }

    private double getCartTotal(Cart cart){
        double grandTotal=0;
        List<CartItem> cartItems = cart.getCartItem();

        for(CartItem item: cartItems){
            grandTotal += item.getPrice();
        }
        return grandTotal;
    }
    @RequestMapping("/cart/getUserCart")
    public Cart getUserCart(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailId = user.getUsername();
        Customer customer = customerService.getCustomerByemailId(emailId);
        Cart c = customer.getCart();
        c.setTotalPrice(getCartTotal(c));
        return c;
    }

    @RequestMapping("/cart/getCart/{cartId}")
    public @ResponseBody Cart getCartItems(@PathVariable(value="cartId")String cartId){
        return cartService.getCartByCartId(cartId);
    }

}