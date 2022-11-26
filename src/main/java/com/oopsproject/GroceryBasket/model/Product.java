package com.oopsproject.GroceryBasket.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 5186013952828648626L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

    @Column(name="category")
    private String productCategory;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "manufacturer")
    private String productManufacturer;

    @NotNull(message = "Product Name is mandatory")
    @Column(name = "name")
    private String productName;

    @NotNull(message="Please provide some price")
    @Min(value = 100, message = "Minimum value should be greater than 100")
    @Column(name = "price")
    private double productPrice;

    @Column(name = "unit")
    private Long unitStock;

    @Column(name = "product_link")
    private String productImage;

    @Column(name= "delivery_date")
    private Long deliveryDate;


    // Constructors
    public Product(String productId, String productCategory, String productDescription, String productManufacturer,
                   String productName, double productPrice, Long unitStock) {
        super();
        this.productId = productId;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productManufacturer = productManufacturer;
        this.productName = productName;
        this.productPrice = productPrice;
        this.unitStock = unitStock;
    }

}
