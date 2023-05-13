package com.example.microservice.rest.example.repository;

import com.example.microservice.rest.example.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
}
