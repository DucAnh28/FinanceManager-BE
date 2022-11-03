package com.codegym.qltcbe.model.dto;

import com.codegym.qltcbe.model.entity.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class JwtResponse {
    private Long id;
    private String username;
    private String token;
    private Set<Role> roleSet;
}
