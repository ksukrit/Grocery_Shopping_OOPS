package com.oopsproject.GroceryBasket.service;


import com.oopsproject.GroceryBasket.model.Cart;

import java.io.IOException;
import java.io.InvalidObjectException;

public interface CartService {

    Cart getCartByCartId(String CartId);

    void validate(String cartId) throws InvalidObjectException;
}
