package com.codegym.qltcbe.controller;

import com.codegym.qltcbe.model.dto.ChangePasswordDTO;
import com.codegym.qltcbe.model.entity.AppUser;
import com.codegym.qltcbe.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        Optional<AppUser> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            AppUser appUser = userOptional.get();
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> editUserById(@PathVariable Long id, @Valid @RequestBody AppUser appUser) {
        Optional<AppUser> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (appUser.getAva() == null) {
                appUser.setAva(userOptional.get().getAva());
            }
            return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<AppUser> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        Optional<AppUser> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (userOptional.get().getPassword().equals(changePasswordDTO.getOldPassword())) {
            userOptional.get().setPassword(changePasswordDTO.getNewPassword());
            return new ResponseEntity<>(userService.save(userOptional.get()), HttpStatus.ACCEPTED);
        } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
