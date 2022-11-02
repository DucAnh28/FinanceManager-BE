package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.dto.JwtResponse;
import com.codegym.qltcbe.model.entity.Role;
import com.codegym.qltcbe.model.entity.User;
import com.codegym.qltcbe.service.JwtService;
import com.codegym.qltcbe.service.role.IRoleService;
import com.codegym.qltcbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody User user) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token mới
            String token = jwtService.createToken(authentication);
            User user1 = userService.getUserByUsername(user.getUsername());
            JwtResponse jwtResponse = new JwtResponse(user1.getId(), user1.getUsername(), token, user1.getRoles());
            return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (userService.getUserByUsername(user.getUsername()) == null){
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(2L).get());
            user.setRoles(roles);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
