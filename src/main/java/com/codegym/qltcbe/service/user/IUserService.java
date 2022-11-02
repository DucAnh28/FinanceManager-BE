package com.codegym.qltcbe.service.user;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<AppUser>, UserDetailsService {
    UserDetails loadUserByUsername(String username);


    AppUser getUserByUsername(String username);
}
