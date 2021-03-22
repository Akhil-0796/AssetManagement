package com.assetmanagement.controller;

import com.assetmanagement.modal.Category;
import com.assetmanagement.repository.CategoryRepository;
import com.assetmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
       return categoryService.addCategory(category);
    }

    @GetMapping("/allCategory")
    public List<Category> categoryList(){
        return categoryService.listAllCategory();
    }

    @PutMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable String id,@RequestBody Category category){
        return categoryService.update(Long.valueOf(id),category);
    }


}
