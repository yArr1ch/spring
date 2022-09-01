package com.course.hotelApi.service;


import com.course.hotelApi.entity.Role;
import com.course.hotelApi.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class Details implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails build(User user) {
        Details details = new Details();
        details.id = user.getId();
        details.username = user.getUsername();
        details.password = user.getPassword();
        details.role = user.getRole();
        details.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
