package com.cybertek.lab3_orm.repository;

import com.cybertek.lab3_orm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Find product by id.
     * @param id
     * @return
     */
    Optional<Product> findById(Integer id);

    /**
     * Find product containing search criteria
     * @param name
     * @return
     */
    @Query(value = "SELECT * from product WHERE name ILIKE :name ORDER BY id", nativeQuery = true)
    List<Product> findByNameNativeQuery(@Param("name") String name);

    /**
     * Find all products by category.
     * @param categoryId
     * @return
     */
    List<Product> findByCategoryId(Integer categoryId);

}
