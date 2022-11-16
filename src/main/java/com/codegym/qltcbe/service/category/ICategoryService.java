package com.codegym.qltcbe.service.category;

import com.codegym.qltcbe.model.entity.Category;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICategoryService {
    Category saveOrEdit(Category category,Long userId);
    Category save(Category category);

    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void delete(Long id);

    Iterable<Category> findAllByUserAndStatus(Long id);

    Category findDefaultCategoryByUser(Long id);

//    Category checkDefaultCategory(Category category, Long userId);

}
