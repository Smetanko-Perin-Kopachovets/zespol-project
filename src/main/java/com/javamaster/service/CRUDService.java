package com.javamaster.service;

import java.util.List;

public interface CRUDService<T> {

    T create(T t);

    void delete(Long id);

    T getById(Long id);

    T update(T t);

    List<T> getAll();

}
