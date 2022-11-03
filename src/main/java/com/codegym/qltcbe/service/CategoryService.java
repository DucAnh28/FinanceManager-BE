package com.codegym.qltcbe.service;

import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.repo.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Category> findAllByStatus(int num, Long id) {
        return null;
    }

    @Override
    public Iterable<Category> findAllByUserId(Long id) {
        return null;
    }
}

