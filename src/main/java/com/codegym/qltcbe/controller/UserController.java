package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

}
