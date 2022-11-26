package com.oopsproject.GroceryBasket.service;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Product;

public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getProductsByName(String name);

    Product getProductById(String productId);

    void deleteProduct(String productId);

    void addProduct(Product product);

    void editProduct(Product product);

    List<Product> getProductsByCategory(String cat);

    List<Product> getFeaturedProducts();
}
