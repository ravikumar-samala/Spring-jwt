package com.example.microservice.rest.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;
}
