package com.javamaster.service.sec;

import com.javamaster.dao.impl.UserDaoImpl;
import com.javamaster.model.Role;
import com.javamaster.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDaoImpl.getByEmail(email);
        if(user != null) {
            System.out.println("get user with email - " + user.getEmail() + " and role " + user.getRole());
            List<String> roles = new ArrayList<>();
            Role userRole = user.getRole();
            roles.add(userRole.getName());
            return new UserDetailsImpl(user, roles);
        }
        throw new UsernameNotFoundException("User with this email do not exist");
    }
}
