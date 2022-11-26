package com.oopsproject.GroceryBasket.service;


import java.util.List;

import com.oopsproject.GroceryBasket.model.User;

public interface UserService {

    List<User> getAllUsers();

    void deleteUser(String userId);

    void addUser(User user);

    User getUserById(String userId);

    User getUserByEmailId(String emailId);

    void updatePassword(User user);


}
