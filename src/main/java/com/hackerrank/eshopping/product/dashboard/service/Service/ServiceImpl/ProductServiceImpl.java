package com.hackerrank.eshopping.product.dashboard.service.Service.ServiceImpl;

import com.hackerrank.eshopping.product.dashboard.service.Repository.domain.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.service.Repository.ProductRepository;
import com.hackerrank.eshopping.product.dashboard.service.Domain.Productdto;
import com.hackerrank.eshopping.product.dashboard.service.Service.ProductService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository ;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Productdto createProduct(Productdto product)  {
        Productdto returnvalue = new Productdto();

            ProductEntity productEntity = new ProductEntity();
            BeanUtils.copyProperties(product, productEntity);


            ProductEntity storedProduct = productRepository.save(productEntity);


            BeanUtils.copyProperties(storedProduct, returnvalue);


        return returnvalue;
    }

    @Override
    public List<Productdto> getProduct() throws NotFoundException {
        List<Productdto> returnvalue = new ArrayList<>();
        Iterable<ProductEntity> allproduct = productRepository.findAll();

        long size = allproduct.spliterator().getExactSizeIfKnown();
        if(size == 0){
            throw new NotFoundException("There is no product in the database");
        }

        for(ProductEntity productEntity : allproduct){
            Productdto productdto = new Productdto();
            BeanUtils.copyProperties(productEntity,productdto);
            returnvalue.add(productdto);
        }
        return returnvalue;
    }

    @Override
    public Productdto getProductbyid(Long id) throws NotFoundException {
        Productdto returnvalue = new Productdto();
        Iterable<ProductEntity> existingproduct = productRepository.findAll();

        for (ProductEntity productEntity: existingproduct) {

            if ((productEntity.getId()).equals(id)) {
                ProductEntity foundproduct = productRepository.getProductbyid(id);
                BeanUtils.copyProperties(foundproduct,returnvalue);
                return returnvalue;

            }

        }

        throw new NotFoundException("Product with ID: "+id+" not found");
    }

    @Override
    public List<Productdto> getProductbycategory(String category) throws NotFoundException {

        List<Productdto> returnvalue = new ArrayList<>();
        Iterable<ProductEntity> productbyCategory = productRepository.getProductbyCategory(category);

        for (ProductEntity productEntity: productbyCategory){
                Productdto productdto = new Productdto();
                BeanUtils.copyProperties(productEntity, productdto);
                returnvalue.add(productdto);
            }

        if(!returnvalue.isEmpty()) {
            return returnvalue;
        }

            throw new NotFoundException("There is no product with this category");
        }



    @Override
    public List<Productdto> getProductbycategoryandavail(String category, boolean availability) throws NotFoundException {
        List<Productdto> returnvalue = new ArrayList<>();
        List <ProductEntity> prodcategoryandavail = productRepository.getProductbyCategoryandavailability(category,availability);

        for (ProductEntity productEntity : prodcategoryandavail) {
            Productdto productdto = new Productdto();
            BeanUtils.copyProperties(productEntity, productdto);
            returnvalue.add(productdto);
        }
        if(!returnvalue.isEmpty()) {
            return returnvalue;
        }
        throw new NotFoundException("There is no product with this requirement");

    }

    @Override
    public Productdto updateProduct(Long id, Double retailprice, Double discountedprice, Boolean availability) throws NotFoundException {
        Iterable<ProductEntity> existingproduct = productRepository.findAll();

        for (ProductEntity productEntity: existingproduct) {

            if ((productEntity.getId()).equals(id)) {

                Productdto returnValue ;
                ProductEntity foundproduct = productRepository.getProductbyid(id);


                if(retailprice != null){
                    foundproduct.setRetailPrice(retailprice);
                }

                if(discountedprice != null){
                    foundproduct.setDiscountedPrice(discountedprice);
                }

                if(availability != null){
                    foundproduct.setAvailability(availability);
                }

                ProductEntity updatedProduct = productRepository.save(foundproduct);
                returnValue = new ModelMapper().map(updatedProduct,Productdto.class);



                return returnValue;
            }

        }
        throw new NotFoundException("Id does not exist");
        }


    @Override
    public void deleteProduct(Long id) throws NotFoundException {
        ProductEntity soontobedeleted = productRepository.getProductbyid(id);

        if(soontobedeleted == null){
            throw new NotFoundException("There is no product with this ID");
        }
        productRepository.delete(soontobedeleted);
    }

}
