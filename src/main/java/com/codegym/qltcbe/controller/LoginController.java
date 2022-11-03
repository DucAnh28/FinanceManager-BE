package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private IUserService userService;


}
