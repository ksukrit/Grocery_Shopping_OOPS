package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.*;
import com.oopsproject.GroceryBasket.service.CartItemService;
import com.oopsproject.GroceryBasket.service.CartService;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
public class CartItemController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;



    @RequestMapping("/cart/add/{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseObject addCartItem(@PathVariable(value = "productId") String productId, @RequestParam(required = false,value = "quantity") String quantity) {
        org.springframework.security.core.userdetails.User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailId = user.getUsername();
        System.out.println(emailId);
        Customer customer = customerService.getCustomerByemailId(emailId);
        System.out.println("Customer : " + customer.getUsers().getEmailId());
        Cart cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItem();
        Product product = productService.getProductById(productId);
        int itemQ;
        if(quantity == null){
            itemQ = 1;
        }else{
            itemQ = Integer.parseInt(quantity);
        }
        System.out.println(product);
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (product.getProductId().equals(cartItem.getProduct().getProductId())) {
                cartItem.setQuality(cartItem.getQuality() + itemQ);
                cartItem.setPrice(cartItem.getQuality() * cartItem.getProduct().getProductPrice());
                cartItemService.addCartItem(cartItem);
                return new ResponseObject(200,"ADDED_SUCCESSFULLY","Added");

            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuality(itemQ);
        cartItem.setProduct(product);
        cartItem.setPrice(product.getProductPrice() * itemQ);
        cartItem.setCart(cart);

        cartItemService.addCartItem(cartItem);
        try {
            cartService.validate(cart.getCartId());
        } catch (InvalidObjectException e) {
            return new ResponseObject(500,"FAILED_TO_ADD","Failed to add product to cart");
        }
        return new ResponseObject(200,"ADDED_SUCCESSFULLY","Added");

    }

    @RequestMapping("/cart/removeCartItem/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }

    @RequestMapping("/cart/removeAllItems/{cartId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@PathVariable(value = "cartId") String cartId) {
        Cart cart = cartService.getCartByCartId(cartId);
        cartItemService.removeAllCartItems(cart);
    }

}
