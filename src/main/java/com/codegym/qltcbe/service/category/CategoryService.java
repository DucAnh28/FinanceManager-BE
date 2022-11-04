package com.codegym.qltcbe.service.category;

import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.repo.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);

    }

//    @Override
//    public Iterable<Category> findAllByStatus(int num, Long id) {
//        return categoryRepository.findAllByStatus(num, id);
//    }

//    @Override
//    public Iterable<Category> findAllByUserId(Long id) {
//        return categoryRepository.findAllByUserId(id);
//    }
}

