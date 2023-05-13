package com.example.microservice.rest.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissions extends BaseEntity {

    @ManyToOne
    private Role role;

    @Column
    private String permission;
}
