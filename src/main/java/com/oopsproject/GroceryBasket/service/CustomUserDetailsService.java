package com.oopsproject.GroceryBasket.service;

import com.oopsproject.GroceryBasket.dao.UserDao;
import com.oopsproject.GroceryBasket.model.Authorities;
import com.oopsproject.GroceryBasket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByEmailId(username);
        Authorities authorities = userDao.getUserAuthorities(username);
        if(user == null || authorities == null){
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmailId())
                .password("{noop}" + user.getPassword() )
                .roles(authorities.getAuthorities())
                .build();

        System.out.println("username is " + authorities.getEmailId() + " auths are " + authorities.getAuthorities());

        return userDetails;
    }
}
