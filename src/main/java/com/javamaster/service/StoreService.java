package com.javamaster.service;

import com.javamaster.model.Store;

import java.util.List;

public interface StoreService {

    Store create(Store store);

    void delete(Long id);

    Store getById(Long id);

    Store update(Store store);

    List<Store> getAll();

}
