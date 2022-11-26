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

    @RequestMapping("getProductById/{productId}")
    public Product getProductById(@PathVariable(value = "productId") String productId) {
        return productService.getProductById(productId);
    }

//    @RequestMapping("/admin/delete/{productId}")
//    public String deleteProduct(@PathVariable(value = "productId") String productId) {
//
//        // Here the Path class is used to refer the path of the file
//
//        Path path = Paths.get("C:/Users/Ismail/workspace/ShoppingCart/src/main/webapp/WEB-INF/resource/images/products/"
//                + productId + ".jpg");
//
//        if (Files.exists(path)) {
//            try {
//                Files.delete(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        productService.deleteProduct(productId);
//        // http://localhost:8080/shoppingCart/getAllProducts
//        return "redirect:/getAllProducts";
//    }

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
        // Binding Result is used if the form that has any error then it will
        // redirect to the same page without performing any functions
//        if (result.hasErrors())
//            return "addProduct";
//        productService.addProduct(product);
//        MultipartFile image = product.getProductImage();
//        if (image != null && !image.isEmpty()) {
//            Path path = Paths
//                    .get("C:/Users/Ismail/workspace/ShoppingCart/src/main/webapp/WEB-INF/resource/images/products/"
//                            + product.getProductId() + ".jpg");
//
//            try {
//                image.transferTo(new File(path.toString()));
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
        return "redirect:/getAllProducts";
    }

    @RequestMapping(value = "/admin/product/editProduct/{productId}")
    public ModelAndView getEditForm(@PathVariable(value = "productId") String productId) {
        Product product = productService.getProductById(productId);
        return new ModelAndView("editProduct", "editProductObj", product);
    }

    @RequestMapping(value = "/admin/product/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute(value = "editProductObj") Product product) {
        productService.editProduct(product);
        return "redirect:/getAllProducts";
    }

    @RequestMapping("/getProductsList")
    public @ResponseBody List<Product> getProductsListInJson() {
        return productService.getAllProducts();
    }

    @RequestMapping("/productsListAngular")
    public String getProducts() {
        return "productListAngular";
    }

}
