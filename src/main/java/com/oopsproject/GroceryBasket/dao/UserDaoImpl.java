package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Authorities;
import com.oopsproject.GroceryBasket.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        // TODO: Change this to non deprecated library
        List<User> users=	 session.createCriteria(User.class).list();

        session.close();
        return users;
    }

    public void deleteUser(String userId) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, userId);
        session.saveOrUpdate(user);
        session.flush();
        session.close();// close the session
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public User getUserById(String userId) {
        // Reading the records from the table
        Session session = sessionFactory.openSession();
        // select * from Product where isbn=i
        // if we call get method,Record doesnot exist it will return null
        // if we call load, if the record doesnt exist it will throw exception
        User user = (User) session.get(User.class, userId);
        session.close();
        return user;
    }

    @Override
    public User getUserByEmailId(String emailId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = (User) criteria.add(Restrictions.eq("emailId", emailId))
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public Authorities getUserAuthorities(String emailId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Authorities.class);
        Authorities authorities = (Authorities) criteria.add(Restrictions.eq("emailId", emailId))
                .uniqueResult();
        session.close();
        return authorities;
    }

    @Override
    public void changePassword(User user) {
        Session session = sessionFactory.openSession();
        session.update(user);
        session.close();
    }

    @Override
    public void updateAuthority(String emailId, String type) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Authorities.class);
        Authorities authorities = (Authorities) criteria.add(Restrictions.eq("emailId", emailId))
                .uniqueResult();
        authorities.setAuthorities(type);
        session.update(authorities);
        session.flush();
        session.close();
    }

}
