package com.cybertek.lab3_orm.controller;

import com.cybertek.lab3_orm.model.DTO.SearchDTO;
import com.cybertek.lab3_orm.model.Product;
import com.cybertek.lab3_orm.service.CategoryService;
import com.cybertek.lab3_orm.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "productName", required = false) String productName) {
        model.addAttribute("products", productService.readAllProducts());
        model.addAttribute("search", new SearchDTO());
        model.addAttribute("categories", categoryService.readAllCategories());
        return "product/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "productName", required = false) String productName,
                         @RequestParam(value = "categoryName", required = false) String categoryName,
                         Model model) {
        SearchDTO searchDTO = SearchDTO.builder()
                .categoryName(categoryName)
                .productName(productName)
                .build();
        model.addAttribute("products", productService.searchProducts(productName, categoryName));
        model.addAttribute("search", searchDTO);
        model.addAttribute("categories", categoryService.readAllCategories());
        return "product/search";
    }

    @GetMapping("/search-product")
    public String search(@ModelAttribute("search") SearchDTO search,
                         RedirectAttributes redirectAttributes) {
        if (!StringUtils.isEmpty(search.getProductName())) {
            redirectAttributes.addAttribute("productName", search.getProductName());
        }
        if (!StringUtils.isEmpty(search.getCategoryName())) {
            redirectAttributes.addAttribute("categoryName", search.getCategoryName());
        }
        return "redirect:/search";
    }

    @GetMapping("/product")
    public String productDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productService.readById(id));
        model.addAttribute("quantity", 1);
        return "product/product-details";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("categories", categoryService.readAllCategories());
        model.addAttribute("product", new Product());
        return "product/add-product";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product product) {
        productService.createOrUpdateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/edit-product")
    public String editProduct(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productService.readById(id));
        model.addAttribute("categories", categoryService.readAllCategories());
        return "product/edit-product";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product) {
        productService.createOrUpdateProduct(product);
        return "redirect:/";
    }

    @PostMapping("/buy-product")
    public String buy(@RequestParam("id") Integer id, Model model, Integer quantity) {
        productService.buyProduct(id, quantity);
        return "redirect:/";
    }
}
