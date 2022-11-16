package com.codegym.qltcbe.service.category;

import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.model.entity.Payment;
import com.codegym.qltcbe.repo.ICategoryRepository;
import com.codegym.qltcbe.service.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category editCategory(Category category, Long userId) {
        if (category.getId() == findDefaultCategoryByUser(userId).getId()) {
            return null;
        } else {
            List<Payment> payments = paymentService.findAllByCategory_Id(category.getId());
            for (int i = 0; i < payments.size(); i++) {
                payments.get(i).setCategory(findDefaultCategoryByUser(category.getAppUser().getId()));
            }
            return categoryRepository.save(category);
        }
    }

    public Category softDelete(Category category, Long userID) {
        if (Objects.equals(category.getId(), findDefaultCategoryByUser(userID).getId())) {
            return null;
        } else {
            List<Payment> payments = paymentService.findAllByCategory_Id(category.getId());
            for (Payment payment : payments) {
                payment.setCategory(findDefaultCategoryByUser(category.getAppUser().getId()));
            }
            category.setStatus(0);
            return categoryRepository.save(category);
        }
    }

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

    @Override
    public Iterable<Category> findAllByUserAndStatus(Long id) {
        return categoryRepository.findAllByUserAndStatus(id);
    }

    @Override
    public Category findDefaultCategoryByUser(Long id) {
        return categoryRepository.findDefaultCategoryByUser(id);
    }
}

