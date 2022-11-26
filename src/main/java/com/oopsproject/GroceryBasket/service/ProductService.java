package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Product;

public interface ProductService {

    public List<Product> getAllProducts();

    Product getProductById(String productId);

    void deleteProduct(String productId);

    void addProduct(Product product);

    void editProduct(Product product);
}
