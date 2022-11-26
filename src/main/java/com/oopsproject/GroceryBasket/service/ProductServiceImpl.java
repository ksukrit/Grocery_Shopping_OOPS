package com.oopsproject.GroceryBasket.service;
import java.util.List;

import com.oopsproject.GroceryBasket.dao.ProductDao;
import com.oopsproject.GroceryBasket.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productDao.getProductByName(name);
    }


    public Product getProductById(String productId) {
        return productDao.getProductById(productId);
    }


    public void deleteProduct(String productId) {
        productDao.deleteProduct(productId);
    }

    public void addProduct(Product product){
        productDao.addProduct(product);
    }

    public void editProduct(Product product){
        productDao.editProduct(product);
    }

    @Override
    public List<Product> getProductsByCategory(String cat) {
        return productDao.getProductByCategory(cat);
    }

    @Override
    public List<Product> getFeaturedProducts() {
        return productDao.getFeaturedProducts();
    }

}
