package com.oopsproject.GroceryBasket.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shippingAddress")
@Data
public class ShippingAddress implements Serializable {

    private static final long serialVersionUID = 7551999649936522523L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String shippingaddressId;

    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;

}
