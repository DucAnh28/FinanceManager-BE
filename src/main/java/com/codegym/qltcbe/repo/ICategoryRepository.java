package com.codegym.qltcbe.repo;

import com.codegym.qltcbe.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

@Query(value = "select * from category c where c.app_user_id = :id and status = 1",nativeQuery = true)
Iterable<Category> findAllByUserAndStatus(@Param("id") Long id);

}

