package com.example.microservice.rest.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Table(name = "users")
public class User extends BaseEntity{


    @Column
    private String name;

    @Column
    private String password;



}
