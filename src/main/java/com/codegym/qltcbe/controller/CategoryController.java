package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Category>> findAllByUser(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findAllByUserAndStatus(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Category> findById(@RequestParam Long cate_id) {
        Optional<Category> categoryOptional = categoryService.findById(cate_id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryService.findById(cate_id).get(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping("{id}")
    private ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoryService.saveOrEdit(category, category.getAppUser().getId()), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        optionalCategory.get().setStatus(0);
        return new ResponseEntity<>(categoryService.saveOrEdit(optionalCategory.get(), optionalCategory.get().getAppUser().getId()), HttpStatus.OK);
    }
}

