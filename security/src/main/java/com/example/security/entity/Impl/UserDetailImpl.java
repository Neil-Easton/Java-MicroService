package com.example.security.entity.Impl;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.security.entity.UserDetail;

public class UserDetailImpl implements UserDetail {

    private String username;
    private String password;
    private boolean active = true;
    private List<GrantedAuthority> authorities;

    public UserDetailImpl(String username, String password, boolean active, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
        return active;
    }

}
