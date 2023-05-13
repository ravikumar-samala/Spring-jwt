package com.example.microservice.rest.example.repository;

import com.example.microservice.rest.example.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    List<UserRole> findAllByUser_Name(String name);
}
