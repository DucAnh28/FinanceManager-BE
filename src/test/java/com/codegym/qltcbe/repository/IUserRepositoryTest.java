package com.codegym.qltcbe.repository;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.repo.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IUserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IUserRepository userRepository;

    @BeforeEach
    void init() {
        AppUser appUser = new AppUser();
        appUser.setUsername("admin");
        appUser.setPassword("Admin123");
        appUser.setEmail("admin@gmail.com");
        entityManager.persist(appUser);
        entityManager.flush();
    }

    @Test
    @DisplayName("Find All")
    public void whenFindAll_thenReturnListNotNull() {
        assertThat(userRepository.findAll()).isNotNull();
    }

}
