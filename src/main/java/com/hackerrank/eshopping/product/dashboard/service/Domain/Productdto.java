package com.hackerrank.eshopping.product.dashboard.service.Domain;

import java.io.Serializable;

public class Productdto implements Serializable {

    private static final long serialVersionUID = 246199L;

    private Long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
    private Boolean availability = false;


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

    public void setRetailPrice(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getDiscountedPrice() {
        return discounted_price;
    }

    public void setDiscountedPrice(Double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


}
