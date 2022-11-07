package com.codegym.qltcbe.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class api {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
