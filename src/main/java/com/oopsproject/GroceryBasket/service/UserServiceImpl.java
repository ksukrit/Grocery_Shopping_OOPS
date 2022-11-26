package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.dao.UserDao;
import com.oopsproject.GroceryBasket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }
    @Transactional
    public void addUser(User user){
        userDao.addUser(user);
    }

    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }


}