package com.oopsproject.GroceryBasket.dao;

import java.io.IOException;

import com.oopsproject.GroceryBasket.model.Cart;

public interface CartDao {

    Cart getCartByCartId(String CartId);

    Cart validate(String cartId) throws IOException;

    void update(Cart cart);
}
