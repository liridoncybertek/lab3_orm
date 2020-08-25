package com.cybertek.lab3_orm.controller;

import com.cybertek.lab3_orm.model.Category;
import com.cybertek.lab3_orm.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.readAllCategories());
        return "category/index";
    }

    @GetMapping("/category-details")
    public String categoryDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.readById(id));
        return "category/category-details";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    @GetMapping("/edit-category")
    public String editCategory(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.readById(id));
        return "category/edit-category";
    }

    @PostMapping("/create-category")
    public String createCategory(@ModelAttribute("category") Category category, Model model) {
        categoryService.createOrUpdate(category);
        model.addAttribute("categories", categoryService.readAllCategories());
        return "redirect:/categories";
    }

    @PostMapping("/update-category")
    public String updateCategory(@RequestParam("id") Integer id, @ModelAttribute("category") Category category, Model model) {
        categoryService.createOrUpdate(category);
        model.addAttribute("categories", categoryService.readAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
