package com.example.microservice.rest.example.domain;

import com.example.microservice.rest.example.repository.RolePermissionsRepositiory;
import com.example.microservice.rest.example.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private User user;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RolePermissionsRepositiory rolePermissionsRepositiory;

    public MyUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> roles = userRoleRepository.findAllByUser_Name(user.getName());
        /*roles.stream()
                .map(a->a.getRole())
                .map(a -> {
                    List<RolePermissions> permissions = rolePermissionsRepositiory.findByRole_Name(a.getName());
                    permissions.stream()
                            .map(b->new SimpleGrantedAuthority(b.getPermission()))
                            .map(c->authorities.add(c));

                    return a;
                })
                .map(a-> new SimpleGrantedAuthority(a.getName()))
                .map(a -> authorities.add(a));*/

        for(UserRole role : roles){
            SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getRole().getName());
            authorities.add(auth);
            List<RolePermissions> permissions = rolePermissionsRepositiory.findByRole_Name(role.getRole().getName());
            for(RolePermissions p: permissions){
                SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority(p.getPermission());
                authorities.add(auth1);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
