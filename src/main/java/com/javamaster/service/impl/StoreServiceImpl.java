package com.javamaster.service.impl;


import com.javamaster.dao.StoreRepository;
import com.javamaster.model.Store;
import com.javamaster.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        return storeRepository.saveAndFlush(store);
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }

    @Override
    public Store getById(Long id) {
        return storeRepository.findOne(id);
    }

    @Override
    public Store update(Store store) {
        return storeRepository.saveAndFlush(store);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

}
