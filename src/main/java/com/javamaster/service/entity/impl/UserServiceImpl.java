package com.javamaster.service.entity.impl;

import com.javamaster.dao.UserRepository;
import com.javamaster.dao.impl.UserDaoImpl;
import com.javamaster.model.User;
import com.javamaster.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id.intValue());
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id.intValue());
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
