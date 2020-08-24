package com.cybertek.lab3_orm.controller;

import com.cybertek.lab3_orm.model.Product;
import com.cybertek.lab3_orm.service.CategoryService;
import com.cybertek.lab3_orm.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.readAllProducts());
        return "/";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Product> products = search.isEmpty() ? productService.readAllProducts() : productService.readAllByContainingName(search);
        model.addAttribute("products", products);
        return "/";
    }

    @GetMapping("/product")
    public String productDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productService.readById(id));
        return "/";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("categories", categoryService.readAllCategories());
        return "/";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product product) {
        productService.createOrUpdateProduct(product);
        return "/";
    }

    @GetMapping("/edit-product")
    public String editProduct(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productService.readById(id));
        model.addAttribute("categories", categoryService.readAllCategories());
        return "/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product) {
        productService.createOrUpdateProduct(product);
        return "/";
    }
}
