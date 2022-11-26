package com.oopsproject.GroceryBasket.dao;

import com.oopsproject.GroceryBasket.model.CustomerOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomerOrder(CustomerOrder customerOrder) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(customerOrder);
        session.flush();
        session.close();
    }

    @Override
    public CustomerOrder getCustomerOrderById(String orderId) {
        Session session = sessionFactory.openSession();
        CustomerOrder c = session.get(CustomerOrder.class,orderId);
        session.close();
        return c;
    }

    public List<CustomerOrder> getCustomerOrderByUserId(String customerId){
        return new ArrayList<>();
    }

}
