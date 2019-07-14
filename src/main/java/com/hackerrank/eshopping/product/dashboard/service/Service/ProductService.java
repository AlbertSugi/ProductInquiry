package com.hackerrank.eshopping.product.dashboard.service.Service;

import com.hackerrank.eshopping.product.dashboard.service.Domain.Productdto;
import javassist.NotFoundException;

import java.util.List;


public interface ProductService {
    Productdto createProduct(Productdto product) ;
    List<Productdto> getProduct() throws NotFoundException;
    Productdto getProductbyid(Long id) throws NotFoundException;
    List<Productdto> getProductbycategory(String category)  throws NotFoundException ;
    List<Productdto> getProductbycategoryandavail(String category, boolean availability) throws NotFoundException;
    Productdto updateProduct(Long id,Double retailprice,Double discountedprice, Boolean availability ) throws NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;

}
