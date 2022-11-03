package com.codegym.qltcbe.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

<<<<<<< HEAD
    void remove(Long id);
}
=======
    void remove(Long id);}
>>>>>>> dev
