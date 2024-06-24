package com.example.security.service.Impl;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.entity.UserDetail;
import com.example.security.entity.Impl.UserDetailImpl;
import com.example.security.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            // password: sj2&^%@#()
            return new UserDetailImpl("user", "$2y$10$tLZwBaoag7MuURcLjNpuKOCJgQltjtQFd8lMLg89o9kthMqRF3HkK", true,
                    Arrays.asList(
                            new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
