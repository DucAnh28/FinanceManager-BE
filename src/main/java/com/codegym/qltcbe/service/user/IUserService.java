package com.codegym.qltcbe.service.user;

import com.codegym.qltcbe.model.entity.User;
import com.codegym.qltcbe.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    UserDetails findByUsername(String username);
}
