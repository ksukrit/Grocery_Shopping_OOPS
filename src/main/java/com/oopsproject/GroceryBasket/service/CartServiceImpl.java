package com.oopsproject.GroceryBasket.service;

import com.oopsproject.GroceryBasket.dao.CartDao;
import com.oopsproject.GroceryBasket.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InvalidObjectException;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    public Cart getCartByCartId(String CartId) {

        return cartDao.getCartByCartId(CartId);
    }

    @Override
    public void validate(String cartId) throws InvalidObjectException {
        cartDao.validate(cartId);
    }

    @Override
    public void saveCart(Cart c) {
        cartDao.add(c);
    }

}
