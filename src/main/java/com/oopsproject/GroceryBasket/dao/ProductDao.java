package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Product;

public interface ProductDao {

    List<Product> getAllProducts();

    List<Product> getProductByName(String name);

    Product getProductById(String productId);

    void deleteProduct(String productId);

    void addProduct(Product product);

    void editProduct(Product product);

    List<Product> getProductByCategory(String cat);

    List<Product> getFeaturedProducts();
}
