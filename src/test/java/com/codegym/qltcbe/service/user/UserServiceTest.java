package com.codegym.qltcbe.service.user;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Role;
import com.codegym.qltcbe.repo.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

public class UserServiceTest {
    private IUserRepository userRepository = Mockito.mock(IUserRepository.class);
    private IUserService userService = new UserService(userRepository);

    @BeforeEach
    void init() {
        Role role = new Role(1L, "ROLE_ADMIN");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        AppUser appUser = new AppUser();
        appUser.setUsername("admin");
        appUser.setPassword("Admin123");
        appUser.setEmail("admin@gmail.com");
        appUser.setStatus(1);
        appUser.setRoles(roleSet);

        doReturn(Optional.of(appUser)).when(userRepository).findById(1L);
        doReturn(appUser).when(userRepository).findByUsername("admin");
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(appUser);
        doReturn(appUserList).when(userRepository).findAll();
    }

    @Test
    @DisplayName("findAll can return list is not null")
    public void whenFindAllNotNull() {
        assertThat(userService.findAll()).isNotNull();
    }

    @Test
    @DisplayName("findByID can return name admin")
    public void whenFindById_thenReturnUser() {
        Optional<AppUser> appUser = userService.findById(1L);
        assertThat(appUser.get().getUsername()).isEqualTo("admin");
    }

    @Test
    @DisplayName("findByUsername can return user with name admin")
    public void whenFindByUsername_thenReturnUser() {
        String username = "admin";
        AppUser appUser = userService.getUserByUsername(username);
        assertThat(appUser.getUsername()).isEqualTo("admin");
    }
}
