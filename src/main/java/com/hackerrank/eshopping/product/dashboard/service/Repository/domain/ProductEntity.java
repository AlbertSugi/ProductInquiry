package com.hackerrank.eshopping.product.dashboard.service.Repository.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product")
public class ProductEntity implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue
    private long autoID;

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 50)
    private String category;

    @Column(name = "retail_price",nullable = false)
    private Double retail_price;

    @Column(name = "discounted_price")
    private Double discounted_price;

    @Column(nullable = false)
    private Boolean availability = false;

    public long getAutoID() {
        return autoID;
    }

    public void setAutoID(long autoID) {
        this.autoID = autoID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetailPrice() {
        return retail_price;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retail_price = retailPrice;
    }

    public Double getDiscountedPrice() {
        return discounted_price;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discounted_price = discountedPrice;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }



}
