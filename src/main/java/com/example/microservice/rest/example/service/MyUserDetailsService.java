package com.example.microservice.rest.example.service;

import com.example.microservice.rest.example.domain.MyUserDetails;
import com.example.microservice.rest.example.domain.User;
import com.example.microservice.rest.example.repository.RolePermissionsRepositiory;
import com.example.microservice.rest.example.repository.UserRepository;
import com.example.microservice.rest.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RolePermissionsRepositiory rolePermissionsRepositiory;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByName(s);
        return new MyUserDetails(user,userRoleRepository,rolePermissionsRepositiory);

    }
}
