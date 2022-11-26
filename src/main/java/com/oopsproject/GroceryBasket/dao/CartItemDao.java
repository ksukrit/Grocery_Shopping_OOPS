package com.oopsproject.GroceryBasket.dao;

import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;

public interface CartItemDao {

    void addCartItem(CartItem cartItem);
    void removeCartItem(String CartItemId);
    void removeAllCartItems(Cart cart);

}