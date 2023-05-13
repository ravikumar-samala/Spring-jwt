package com.example.microservice.rest.example;

import com.example.microservice.rest.example.domain.Role;
import com.example.microservice.rest.example.domain.RolePermissions;
import com.example.microservice.rest.example.domain.User;
import com.example.microservice.rest.example.domain.UserRole;
import com.example.microservice.rest.example.repository.RolePermissionsRepositiory;
import com.example.microservice.rest.example.repository.RoleRepository;
import com.example.microservice.rest.example.repository.UserRepository;
import com.example.microservice.rest.example.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class RestExampleApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	RolePermissionsRepositiory rolePermissionsRepositiory;

	public static void main(String[] args) {
		SpringApplication.run(RestExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role role1 = createRole("USER_L1", Arrays.asList("WRITE","READ","DELETE"));
		Role role2 = createRole("USER_L2",Arrays.asList("READ"));
		Role role3 = createRole("USER_L3", Collections.emptyList());

		createUserRole("shef1",Arrays.asList(role1));
		createUserRole("shef2",Arrays.asList(role2));
		createUserRole("shef3",Arrays.asList(role3));

	}

	private Role createRole(String roleName, List<String> permissions){

		Role role1 = Role.builder().name("ROLE_"+roleName).build();
		role1 = roleRepository.save(role1);
		for(String p : permissions){
			RolePermissions permission = RolePermissions.builder().role(role1).permission(p).build();
			rolePermissionsRepositiory.save(permission);
		}

		return role1;
	}

	private User createUserRole(String userName, List<Role> roles){

		User user = User.builder().name(userName).password(passwordEncoder.encode("1234")).build();
		user = userRepository.save(user);
		for(Role r : roles){
			UserRole userRole = UserRole.builder().role(r).user(user).build();
			userRoleRepository.save(userRole);
		}

		return user;

	}
}
