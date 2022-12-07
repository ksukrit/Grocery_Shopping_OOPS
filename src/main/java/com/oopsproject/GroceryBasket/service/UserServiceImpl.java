package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.dao.UserDao;
import com.oopsproject.GroceryBasket.model.Authorities;
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

    @Override
    public User getUserByEmailId(String emailId) {
        return userDao.getUserByEmailId(emailId);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        User u = userDao.getUserByEmailId(username);
        u.setPassword(newPassword);
        userDao.changePassword(u);
    }


    @Override
    public String getAuthById(String userId) {
        User u = userDao.getUserById(userId);
        if(u == null){
            return "Error getting Authority for user id " + userId;
        }

        return userDao.getUserAuthorities(u.getEmailId()).getAuthorities();
    }

    @Override
    public String getAuthByEmailId(String emailId) {
        return userDao.getUserAuthorities(emailId).getAuthorities();
    }

    @Override
    public void updateAuthority(String emailId, String type) {
        userDao.updateAuthority(emailId,type);
    }

    @Override
    public List<Authorities> getAuthorities() {
        return userDao.getUserAuthorities();
    }


}