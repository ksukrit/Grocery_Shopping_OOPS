package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Authorities;
import com.oopsproject.GroceryBasket.model.User;

public interface UserDao {

    List<User> getAllUsers();

    void deleteUser(String userId);

    void addUser(User user);

    User getUserById(String userId);

    User getUserByEmailId(String emailId);

    Authorities getUserAuthorities(String emailId);

    void changePassword(User user);

    void updateAuthority(String emailId, String type);

    List<Authorities> getUserAuthorities();
}
