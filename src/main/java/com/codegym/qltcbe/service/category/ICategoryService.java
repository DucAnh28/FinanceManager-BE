package com.codegym.qltcbe.service.category;

import com.codegym.qltcbe.model.entity.Category;

import java.util.Optional;

public interface ICategoryService {
    Category save(Category category);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void delete(Long id);



    Iterable<Category> findAllByStatus(int status);

    Iterable<Category> findAllByUserId(Long id);
}
