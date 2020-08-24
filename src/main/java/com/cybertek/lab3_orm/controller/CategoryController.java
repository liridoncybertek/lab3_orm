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
        return "/";
    }

    @GetMapping("/category")
    public String categoryDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.readById(id));
        return "/";
    }

    @GetMapping("/add-category")
    public String addCategory() {
        return "/";
    }

    @GetMapping("/edit-category")
    public String editCategory(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.readById(id));
        return "/";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category) {
        categoryService.createOrUpdate(category);
        return "/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category) {
        categoryService.createOrUpdate(category);
        return "/";
    }

    @GetMapping("/delete-category")
    public String delete(@RequestParam("id") Integer id) {
        categoryService.deleteCategory(id);
        return "/";
    }
}
