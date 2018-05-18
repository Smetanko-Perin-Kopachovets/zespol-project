package com.javamaster.service.entity.impl;

import com.javamaster.dao.RoleRepository;
import com.javamaster.model.Role;
import com.javamaster.service.entity.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findOne(id.intValue());
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }
}
