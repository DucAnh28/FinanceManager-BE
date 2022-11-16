package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.dto.JwtResponse;
import com.codegym.qltcbe.model.dto.LoginForm;
import com.codegym.qltcbe.model.dto.TokenDto;
import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.model.entity.Category;
import com.codegym.qltcbe.model.entity.Role;
import com.codegym.qltcbe.service.JwtService;
import com.codegym.qltcbe.service.category.ICategoryService;
import com.codegym.qltcbe.service.role.IRoleService;
import com.codegym.qltcbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm user) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token mới
            String token = jwtService.createToken(authentication);
            AppUser user1 = userService.getUserByUsername(user.getUsername());
            JwtResponse jwtResponse = new JwtResponse(user1.getId(), user1.getUsername(), token, user1.getRoles());
            return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@Valid @RequestBody AppUser user) {
        if (userService.getUserByUsername(user.getUsername()) == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(2L).get());
            user.setRoles(roles);
            user.setAva("https://toigingiuvedep.vn/wp-content/uploads/2021/05/avatar-trang-hai.jpg");
            user.setStatus(1);
            AppUser appUser = userService.save(user);
            categoryService.save(new Category("Other", userService.getUserByUsername(user.getUsername()), 1));
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/oauth/fb")
    public ResponseEntity<JwtResponse> facebook(@RequestBody TokenDto tokenDto) throws IOException {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String[] fields = {"email", "name"};
        User user = facebook.fetchObject("me", User.class, fields);
        String userName = user.getName();
        AppUser userFace = new AppUser();
        if (userService.getUserByUsername(userName) != null) {
            userFace = userService.getUserByUsername(userName);
        } else {
            userFace.setUsername(user.getName());
            userFace.setPassword("Manchester69");
            userFace.setEmail(user.getEmail());
            userFace.setStatus(1);
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(2L).get());
            userFace.setRoles(roles);
        }
        userService.save(userFace);
        categoryService.save(new Category("Other", userService.getUserByUsername(userFace.getUsername()), 1));
        LoginForm loginForm = new LoginForm(userFace.getUsername(), userFace.getPassword());
        ResponseEntity<JwtResponse> jwtResponseResponseEntity = login(loginForm);
        return jwtResponseResponseEntity;
    }
}
