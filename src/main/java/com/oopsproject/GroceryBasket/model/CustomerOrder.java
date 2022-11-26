package com.oopsproject.GroceryBasket.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "customerorder")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = -6571020025726257848L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String customerOrderId;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "shippingAddressId")
    private ShippingAddress shippingAddress;

    private Long orderDate;

    private Long deliveryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerOrder that = (CustomerOrder) o;
        return customerOrderId != null && Objects.equals(customerOrderId, that.customerOrderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
