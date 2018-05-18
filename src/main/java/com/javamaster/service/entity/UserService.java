package com.javamaster.service.entity;

import com.javamaster.model.User;

public interface UserService extends CRUDService<User> {
    User getUserByEmail(String email);
}
