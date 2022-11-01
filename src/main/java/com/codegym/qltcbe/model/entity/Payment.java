package com.codegym.qltcbe.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long money;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(columnDefinition = "int default 1")
    private int status;
}
