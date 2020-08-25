package com.cybertek.lab3_orm.service;

import com.cybertek.lab3_orm.model.Product;
import com.cybertek.lab3_orm.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService{

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
