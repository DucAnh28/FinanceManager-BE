package com.codegym.qltcbe.repo.user;

import com.codegym.qltcbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
