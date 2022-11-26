package com.oopsproject.GroceryBasket.service;

import com.oopsproject.GroceryBasket.dao.CartDao;
import com.oopsproject.GroceryBasket.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    public CartDao getCartDao() {
        return cartDao;
    }

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public Cart getCartByCartId(String CartId) {

        return cartDao.getCartByCartId(CartId);
    }

}
