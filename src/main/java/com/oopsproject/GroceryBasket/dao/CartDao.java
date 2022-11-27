package com.oopsproject.GroceryBasket.dao;

import java.io.IOException;
import java.io.InvalidObjectException;

import com.oopsproject.GroceryBasket.model.Cart;

public interface CartDao {

    Cart getCartByCartId(String CartId);

    Cart validate(String cartId) throws InvalidObjectException;

    void update(Cart cart);

    void add(Cart c);
}
