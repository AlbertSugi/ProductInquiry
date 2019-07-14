package com.hackerrank.eshopping.product.dashboard.service.Controller;

import com.hackerrank.eshopping.product.dashboard.service.Controller.representation.request.ProductUpdateRequest;
import com.hackerrank.eshopping.product.dashboard.service.Service.ProductService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import com.hackerrank.eshopping.product.dashboard.service.Domain.Productdto;
import com.hackerrank.eshopping.product.dashboard.service.Controller.representation.request.ProductRequest;
import com.hackerrank.eshopping.product.dashboard.service.Controller.representation.response.ProductResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")

public class ProductsController {


    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //addProduct
    //http://localhost:8080/products
    @PostMapping(path = "/products", consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest product) {

        try {
            if (productService.getProductbyid(product.getId()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad");
            }
        } catch (NotFoundException e) {
            ProductResponse returnvalue = new ProductResponse();


            ModelMapper modelMapper = new ModelMapper();
            Productdto productdto = modelMapper.map(product, Productdto.class);

            Productdto createProduct = productService.createProduct(productdto);
            returnvalue = modelMapper.map(createProduct, ProductResponse.class);


        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");


    }

    ;




    //get all product
    //http://localhost:8080/products/
    @GetMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getProduct() {
        List<ProductResponse> returnValue = new ArrayList<>();

        try {
            List<Productdto> products = productService.getProduct();
            for (Productdto productdto : products) {
                ProductResponse productmodel = new ProductResponse();
                BeanUtils.copyProperties(productdto, productmodel);
                returnValue.add(productmodel);

            }
            return ResponseEntity.status(HttpStatus.OK).body(returnValue);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }

    ;


    //get product by ID
    //http://localhost:8080/products/1 1 is id
    @GetMapping(path = "/products/{productId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> getProductbyId(@PathVariable("productId") Long id) {
        ProductResponse returnvalue;
        Productdto productdto;

        try {
            productdto = productService.getProductbyid(id);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        returnvalue = new ModelMapper().map(productdto, ProductResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);

    }

    ;

    //get product by category

    @GetMapping(path = "/products", params = {"category"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getProductbyCategory(@RequestParam(value = "category") String category) {
        List<ProductResponse> returnvalue = new ArrayList<>();
        List<Productdto> productbycategory;
        try {
            productbycategory = productService.getProductbycategory(category);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(returnvalue); //better change to 404 Response
        }

        for (Productdto productdto : productbycategory) {
            System.out.println(productdto.getId());
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(productdto, productResponse);
            returnvalue.add(productResponse);

        }

        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);


    };


    //return product by category and availability
    @GetMapping(path = "/products", params = {"category", "availability"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProductResponse>> getProductbyCategoryandAvail(@RequestParam(value = "category") String category, @RequestParam(value = "availability") Integer availability) {
        List<ProductResponse> returnvalue = new ArrayList<>();
        List<Productdto> productdtos;

        //temporary, in the future we can implement text classification here
       String newUrlcategory = category.replaceAll("%20", " ");
       if (category.equals("Full%20Body%20Outfits")){
           newUrlcategory = category.replaceAll("%20", " ");
       }
       else if (category.equals( "FullBodyOutfits")){
           newUrlcategory = "Full Body Outfits";
       }
        boolean realavail = (availability == 1);




        try {
            /*System.out.println(newUrlcategory);
            System.out.println(realavail);*/
             productdtos = productService.getProductbycategoryandavail(newUrlcategory, realavail);


        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(returnvalue); //better change to 404 Response
        }
        for (Productdto productdto : productdtos) {
            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(productdto, productResponse);
            returnvalue.add(productResponse);
        }

        return ResponseEntity.status(HttpStatus.OK).body(returnvalue);
    }

    ;


    @PutMapping(path = "/products/{productId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long id, @RequestBody ProductUpdateRequest updateRequest) {

        Productdto productdto = new Productdto();


        try {
            productdto = productService.updateProduct(id, updateRequest.getRetail_price(), updateRequest.getDiscounted_price(), updateRequest.getAvailability());
            return ResponseEntity.status(HttpStatus.OK).body("updated");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    //http://localhost:8080/products/3
    @DeleteMapping(path = "products/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteProduct(@PathVariable Long id) {


        try {
            productService.deleteProduct(id);

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


        return ResponseEntity.status(HttpStatus.OK).body("Delete Success");

    }



}


