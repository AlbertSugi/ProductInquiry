package com.hackerrank.eshopping.product.dashboard.service.Controller.representation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductUpdateRequest {

    private Double retail_price;
    private Double discounted_price;
    private Boolean availability;


    public Double getRetail_price() {
        return retail_price;
    }
    @JsonProperty("retail_price")
    public void setRetail_price(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getDiscounted_price() {
        return discounted_price;
    }

    @JsonProperty("discounted_price")
    public void setDiscounted_price(Double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
