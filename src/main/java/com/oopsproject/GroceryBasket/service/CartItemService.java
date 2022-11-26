package com.oopsproject.GroceryBasket.service;

import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;

public interface CartItemService {

    void addCartItem(CartItem cartItem);
    void removeCartItem(String CartItemId);
    void removeAllCartItems(Cart cart);
}