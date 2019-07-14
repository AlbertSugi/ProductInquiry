package com.hackerrank.eshopping.product.dashboard.service.Controller.representation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {

    private Long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
    private Boolean availability;


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

    @JsonProperty("retail_price")
    public void setRetailPrice(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getDiscountedPrice() {
        return discounted_price;
    }

    @JsonProperty("discounted_price")
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
