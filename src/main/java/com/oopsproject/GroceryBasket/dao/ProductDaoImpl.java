package com.oopsproject.GroceryBasket.dao;

import java.util.List;

import com.oopsproject.GroceryBasket.model.Product;
import com.oopsproject.GroceryBasket.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

    // this class is wired with the sessionFactory to do some operation in the
    // database

    @Autowired
    private SessionFactory sessionFactory;

    // this will create one sessionFactory for this class
    // there is only one sessionFactory should be created for the applications
    // we can create multiple sessions for a sessionFactory
    // each session can do some functions

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAllProducts() {
        // Reading the records from the table
        Session session = sessionFactory.openSession();
        // List<Product> products = session.createQuery("from Product").list();
        List<Product> products = session.createCriteria(Product.class).list();
        System.out.println("----- List of Products-----");
        System.out.println(products);
        // session.flush is used for clear cache in the session
        session.flush();
        // it will close the particular session after completing the process
        session.close();
        return products;
    }

    @Override
    public List<Product> getProductByName(String name) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> products = criteria.add(Restrictions.ilike("productName", name +"%")).list();
        session.close();
        return products;
    }

    public Product getProductById(String productId) {

        // Reading the records from the table
        Session session = sessionFactory.openSession();
        // select * from Product where isbn=i
        Product product = (Product) session.get(Product.class, productId);
        session.close();
        return product;
    }

    public void deleteProduct(String productId) {
        Session session = sessionFactory.openSession();
        Product product = (Product) session.get(Product.class, productId);
        session.delete(product);
        session.flush();
        session.close();// close the session
    }

    public void addProduct(Product product) {
        Session session = sessionFactory.openSession();
        product.setDeliveryDate(172800L);
        product.setFeatured(product.getPromo() > 0);
        session.save(product);

        session.flush();
        session.close();
    }

    public void editProduct(Product product) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(product);
        session.flush();
        session.close();
    }

    @Override
    public List<Product> getProductByCategory(String cat) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> products = criteria.add(Restrictions.like("productCategory", cat)).list();
        session.close();
        return products;
    }

    @Override
    public List<Product> getFeaturedProducts() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> products = criteria.add(Restrictions.eq("isFeatured", true)).list();
        session.close();
        return products;
    }

}
