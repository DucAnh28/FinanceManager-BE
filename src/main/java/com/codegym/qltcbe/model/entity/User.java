package com.codegym.qltcbe.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)",unique = true,nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(255)",unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column()
    private String phone;

    @Column(columnDefinition = "int default 1")
    private int status;

    @Column()
    private String ava;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
