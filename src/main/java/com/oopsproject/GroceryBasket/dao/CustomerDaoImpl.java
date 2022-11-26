package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Authorities;
import com.oopsproject.GroceryBasket.model.Cart;
import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCustomer(Customer customer) {
        System.out.println("Adding customer in dao");
        Session session = sessionFactory.openSession();
        //customer - has users,shippingaddress
        //insert the users,billingaddress
        customer.getUsers().setEnabled(true);

        Authorities authorities = new Authorities();
        authorities.setAuthorities("USER");
        authorities.setEmailId(customer.getUsers().getEmailId());

        Cart cart = new Cart();
        //it is to set CartId for customer table
        customer.setCart(cart);//set the cart to the customer
        //if we omit this statement, hen it will insert null for customerid in cart
        //to set the customerid in cart table
        cart.setCustomer(customer);

        session.save(customer);
        session.save(authorities);
        session.flush();
        session.close();
    }

    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        List<Customer> customerList = session.createQuery("from Customer").list();

        return customerList;
    }

    public Customer getCustomerByemailId(String emailId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from User where emailId=?");
        query.setString(0, emailId);
        User users = (User)query.uniqueResult();
        Customer customer = users.getCustomer();
        return customer;
    }


}