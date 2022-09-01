package com.dataart.intern.logista.service;

import com.dataart.intern.logista.model.Role;
import com.dataart.intern.logista.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserDetailsImp implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImp build(User user) {
        UserDetailsImp userDetailsImp = new UserDetailsImp();
        userDetailsImp.id = user.getId();
        userDetailsImp.username = user.getUsername();
        userDetailsImp.password = user.getPassword();
        userDetailsImp.role = user.getRole();
        userDetailsImp.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return userDetailsImp;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImp user = (UserDetailsImp) obj;
        return Objects.equals(id, user.id);
    }
}
