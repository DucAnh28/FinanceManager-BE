package com.codegym.qltcbe.repo.user;

import com.codegym.qltcbe.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
