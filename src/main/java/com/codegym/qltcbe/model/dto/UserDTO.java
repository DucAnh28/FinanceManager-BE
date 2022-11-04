package com.codegym.qltcbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String ava;
}
