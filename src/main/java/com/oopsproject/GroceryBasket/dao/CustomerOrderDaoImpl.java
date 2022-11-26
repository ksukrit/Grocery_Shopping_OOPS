package com.oopsproject.GroceryBasket.dao;

import com.oopsproject.GroceryBasket.model.CustomerOrder;
import com.oopsproject.GroceryBasket.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<CustomerOrder> getCustomerOrderByCustomerId(String customerId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(CustomerOrder.class);
        List<CustomerOrder> customerOrderList = criteria.add(Restrictions.eq("customer.customerId",customerId)).list();
        Set<CustomerOrder> s = new HashSet<>(customerOrderList);
        List<CustomerOrder> c = new ArrayList<>(s);
        return c;
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        Session session = sessionFactory.openSession();
        List<CustomerOrder> customerOrderList = session.createCriteria(CustomerOrder.class).list();
        Set<CustomerOrder> s = new HashSet<>(customerOrderList);
        List<CustomerOrder> c = new ArrayList<>(s);
        return c;
    }
}
