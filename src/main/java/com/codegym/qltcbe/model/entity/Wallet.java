package com.codegym.qltcbe.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@RequiredArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private long money;

    @Column(columnDefinition = "int default 1")
    private int status;

    @Column(columnDefinition = "varchar(255)")
    private String icon;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
