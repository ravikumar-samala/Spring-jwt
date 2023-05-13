package com.example.microservice.rest.example.repository;

import com.example.microservice.rest.example.domain.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionsRepositiory  extends JpaRepository<RolePermissions,Integer> {

    List<RolePermissions> findByRole_Name(String name);
}
