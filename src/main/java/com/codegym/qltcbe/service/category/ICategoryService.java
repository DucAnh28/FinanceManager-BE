package com.codegym.qltcbe.service.category;

import com.codegym.qltcbe.model.entity.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface ICategoryService {
    Category save(Category category);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void delete(Long id);

    Iterable<Category> findAllByUserId(@PathVariable Long id);

}
