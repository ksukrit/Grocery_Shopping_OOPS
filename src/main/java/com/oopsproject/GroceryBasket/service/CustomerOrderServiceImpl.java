package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.dao.CartDao;
import com.oopsproject.GroceryBasket.dao.CustomerOrderDao;
import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.CartItem;
import com.oopsproject.GroceryBasket.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Autowired
    private CartDao cartDao;

    public void addCustomerOrder(CustomerOrder customerOrder) {
        customerOrderDao.addCustomerOrder(customerOrder);
    }

    public double getCustomerOrderGrandTotal(String cartId) {
        double grandTotal=0;
        Cart cart = cartDao.getCartByCartId(cartId);
        List<CartItem> cartItems = cart.getCartItem();

        for(CartItem item: cartItems){
            grandTotal += item.getPrice();
        }
        return grandTotal;
    }

    @Override
    public CustomerOrder getCustomerOrderById(String orderId) {
        return customerOrderDao.getCustomerOrderById(orderId);
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return customerOrderDao.getAllOrders();
    }

    @Override
    public List<CustomerOrder> getCustomerOrderByCustomerId(String customerId) {
        return customerOrderDao.getCustomerOrderByCustomerId(customerId);
    }

    @Override
    public List<CustomerOrder> getCustomerOrderInRange(Long fromTime, Long toTime) {
        return customerOrderDao.getOrderInRange(fromTime,toTime);
    }

}
