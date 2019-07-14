package com.hackerrank.eshopping.product.dashboard.service.Repository;

import com.hackerrank.eshopping.product.dashboard.service.Repository.domain.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Long> {

    @Query(value = "select * from product p where p.id = :id ",nativeQuery = true)
    ProductEntity getProductbyid(@Param("id") long id);

    @Query(value="select * from product p where p.category = :category ORDER BY p.availability DESC ,p.discounted_price ASC,p.id ASC",nativeQuery = true)
    List<ProductEntity> getProductbyCategory(@Param("category") String category );

    @Query(value= " select ROUND((((p.retail_price - p.discounted_price)/p.retail_price))*100) as discount_percentage, * from product p where p.category = :category AND p.availability = :availability " +
            "ORDER BY discount_percentage  DESC  ,p.discounted_price ASC, p.id ASC",nativeQuery = true)
   List<ProductEntity> getProductbyCategoryandavailability(@Param("category") String category,@Param("availability") boolean availability);

}
