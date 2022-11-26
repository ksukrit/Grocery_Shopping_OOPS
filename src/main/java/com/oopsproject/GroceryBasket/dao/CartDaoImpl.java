package com.oopsproject.GroceryBasket.dao;
import java.io.IOException;
import java.util.List;

import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;


    public Cart getCartByCartId(String CartId) {
        Session session = sessionFactory.openSession();
        Cart cart = (Cart) session.get(Cart.class, CartId);
        // System.out.println(cart.getCartId() + " " + cart.getCartItem());
        System.out.println(cart);
        session.close();
        return cart;

    }

    public Cart validate(String cartId) throws IOException {
        Cart cart = getCartByCartId(cartId);
        if (cart == null || cart.getCartItem().size() == 0) {
            throw new IOException(cartId + "");
        }
        update(cart);
        return cart;
    }

    private double getCartTotal(Cart cart){
        double grandTotal=0;
        List<CartItem> cartItems = cart.getCartItem();

        for(CartItem item: cartItems){
            grandTotal += item.getPrice();
        }
        return grandTotal;
    }

    public void update(Cart cart) {


        double grandTotal = getCartTotal(cart);
        cart.setTotalPrice(grandTotal);

        Session session = sessionFactory.openSession();
        session.saveOrUpdate(cart);
        session.flush();
        session.close();
    }

}
