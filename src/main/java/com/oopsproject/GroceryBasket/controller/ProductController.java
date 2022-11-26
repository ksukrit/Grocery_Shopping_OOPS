package com.oopsproject.GroceryBasket.controller;

import com.oopsproject.GroceryBasket.model.Product;
import com.oopsproject.GroceryBasket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/getProductById/{productId}")
    public Product getProductById(@PathVariable(value = "productId") String productId) {
        return productService.getProductById(productId);
    }

    // Search function
    @RequestMapping("/getProductListByName")
    public List<Product> getProductsByName(@RequestParam String name){
        return productService.getProductsByName(name);
    }

    @RequestMapping("/getProductListByCategory")
    public List<Product> getProductsByCategory(@RequestParam String cat) {
        return productService.getProductsByCategory(cat);
    }

    @RequestMapping("/getFeaturedProducts")
    public List<Product> getFeaturedProducts(){
        return productService.getFeaturedProducts();
    }

    @RequestMapping("/admin/delete/{productId}")
    public String deleteProduct(@PathVariable(value = "productId") String productId) {
        productService.deleteProduct(productId);
        return "redirect:/getAllProducts";
    }

    @RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.GET)
    public String getProductForm(Model model) {
        Product product = new Product();
        // New Arrivals
        // set the category as 1 for the Book book
        product.setProductCategory("Android");
        model.addAttribute("productFormObj", product);
        return "addProduct";
    }

    @RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute(value = "productFormObj") Product product, BindingResult result) {
        if (result.hasErrors())
            return "addProduct";
        productService.addProduct(product);
        return "redirect:/getAllProducts";
    }

    @RequestMapping(value = "/admin/product/editProduct/{productId}")
    public ModelAndView getEditForm(@PathVariable(value = "productId") String productId) {
        Product product = productService.getProductById(productId);
        return new ModelAndView("editProduct", "editProductObj", product);
    }

    @RequestMapping(value = "/admin/product/editProduct", method = RequestMethod.POST)
    public String editProduct(@RequestBody Product product) {
        productService.editProduct(product);
        return "redirect:/getAllProducts";
    }

}
