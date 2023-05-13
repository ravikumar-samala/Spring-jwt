package com.example.microservice.rest.example.repository;

import com.example.microservice.rest.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByName(String name);
}
