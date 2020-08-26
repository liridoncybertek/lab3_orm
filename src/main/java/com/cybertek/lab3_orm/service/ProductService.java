package com.cybertek.lab3_orm.service;

import com.cybertek.lab3_orm.model.Product;
import com.cybertek.lab3_orm.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductService {

    private final EntityManager entityManager;

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public List<Product> readAllProducts() {
        return productRepository.findAll();
    }

    public Product readById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> readAllByContainingName(String name) {
        return productRepository.findByNameNativeQuery(name);
    }

    public List<Product> readAllByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> searchProducts(String productName, String categoryName) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM products p INNER JOIN categories c on p.category_id = c.id ");
        if(!StringUtils.isEmpty(productName) && StringUtils.isEmpty(categoryName)) {
            query.append("WHERE p.name ILIKE '").append(productName).append("'");
        }
        if(StringUtils.isEmpty(productName) && !StringUtils.isEmpty(categoryName)) {
            query.append("WHERE c.name ILIKE '").append(categoryName).append("'");
        }
        if(!StringUtils.isEmpty(productName) && !StringUtils.isEmpty(categoryName)) {
            query.append("WHERE p.name ILIKE '")
                    .append(productName).append("'")
                    .append(" AND c.name = '")
                    .append(categoryName).append("'");
        }
        return entityManager.createNativeQuery(query.toString(), Product.class).getResultList();
    }
}
