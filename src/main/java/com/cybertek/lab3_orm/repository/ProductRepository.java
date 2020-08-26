package com.cybertek.lab3_orm.repository;

import com.cybertek.lab3_orm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Find product by id.
     *
     * @param id
     * @return
     */
    Optional<Product> findById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products SET quantity = (quantity - :subtractQuantity) WHERE id = :productId", nativeQuery = true)
    void updateQuantity(@Param("subtractQuantity") Integer subtractQuantity, @Param("productId") Integer productId);


}
