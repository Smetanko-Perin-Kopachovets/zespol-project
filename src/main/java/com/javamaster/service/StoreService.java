package com.javamaster.service;


import com.javamaster.dao.StoreDao;
import com.javamaster.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StoreService {

    private StoreDao storeDao;

    @Autowired
    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public void create(String name, String city) {
        Store store = new Store(name, city);
        storeDao.create(store);
    }

    public ArrayList<Store> getAll() {
        return storeDao.getAll();
    }

    public void update(Long id, String name, String city) {
        Store store = new Store(id, name, city);
        storeDao.update(store);
    }

    public void delete(Long id) {
        storeDao.deleteById(id);
    }

    public Store getById(Long id) {
        return storeDao.marketById(id);
    }
}
