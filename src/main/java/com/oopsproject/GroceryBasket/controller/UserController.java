package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Customer;
import com.oopsproject.GroceryBasket.model.ResponseObject;
import com.oopsproject.GroceryBasket.model.ShippingAddress;
import com.oopsproject.GroceryBasket.model.User;
import com.oopsproject.GroceryBasket.service.CustomerService;
import com.oopsproject.GroceryBasket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public List<User> getUserList(){
        return userService.getAllUsers();
    }

    @RequestMapping("/customer/list")
    public List<Customer> getCustomerList(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/register")
    public ResponseObject addCustomer(@RequestParam MultiValueMap<String, String> userFormData){
        Customer c = new Customer();
        User u = new User();
        u.setEmailId(userFormData.getFirst("username"));
        u.setPassword(userFormData.getFirst("password"));
        c.setUsers(u);
        c.setFirstName(userFormData.getFirst("fname"));
        c.setLastName(userFormData.getFirst("lname"));
        c.setCustomerPhone(userFormData.getFirst("phoneNo"));

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(userFormData.getFirst("address"));
        shippingAddress.setCity(userFormData.getFirst("city"));
        shippingAddress.setZipcode(userFormData.getFirst("pincode"));
        shippingAddress.setState(userFormData.getFirst("state"));
        shippingAddress.setCountry(userFormData.getFirst("country"));

        c.setShippingAddress(shippingAddress);

        customerService.addCustomer(c);

        return new ResponseObject(200,"USER_CREATED","User created successfully " + c.getUsers().getUserId());
    }

    // Returns the user id for a given email id
    @RequestMapping("/getUserId/{emailId}")
    public String getUserId(@PathVariable(value = "emailId") String emailId){
        return userService.getUserByEmailId(emailId).getUserId();
    }

    // Returns the user id of the currently logged in user
    @RequestMapping("/getUserId")
    public String getUserId(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserByEmailId(user.getUsername()).getUserId();
    }

    @RequestMapping("/getUserType/{userId}")
    public String getUserType(@PathVariable(value = "userId") String userId){
        return userService.getAuthById(userId);
    }

    @RequestMapping("/getUserTypeByEmail/{emailId}")
    public String getUserTypeByEmail(@PathVariable(value = "emailId") String emailId){
        return userService.getAuthByEmailId(emailId);
    }

    @RequestMapping("/user/changePassword")
    public void updatePassword(@RequestParam String newPassword){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updatePassword(user.getUsername(),newPassword);
    }

    @RequestMapping("/admin/upgradeUser/{emailId}")
    public String upgradeUser(@PathVariable(value="emailId") String emailId,@RequestParam String type){
        if(!(type.equals("ADMIN") || type.equals("USER") || type.equals("MANAGER"))){
            return "Invalid type";
        }
        userService.updateAuthority(emailId,type);

        return "Successfully upgraded user";
    }

    @RequestMapping("/getCurrentBalance")
    public String getBalance(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) return "";
        return String.valueOf(customerService.getCustomerByemailId(user.getUsername()).getWalletBalance());
    }

    @RequestMapping("/addToWallet")
    public String updateWallet(@RequestParam String amount){
        double amt = Double.parseDouble(amount);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user == null) return "";
        Customer customer = customerService.getCustomerByemailId(user.getUsername());
        customer.setWalletBalance(customer.getWalletBalance()+amt);
        customerService.updateCustomer(customer);
        return "Successfully added " + amount;
    }


    @RequestMapping("/admin/deleteUser")
    public void deleteUser(){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteUser(userService.getUserByEmailId(user.getUsername()).getUserId());
    }

    @RequestMapping("/admin/deleteUser/{userId}")
    public ResponseObject deleteUser(@PathVariable(value="userId") String userId){
        userService.deleteUser(userId);
        return new ResponseObject(200,"User Deleted","User " + userId + " Deleted");
    }

}
