package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.entity.dto.AuthResponseDto;
import com.example.security.entity.dto.LoginDto;
import com.example.security.filter.JWTGenerator;

@RestController
public class HomeController {

    final private AuthenticationManager authenticationManager;

    final private JWTGenerator tokenGenerator;

    @Autowired
    public HomeController(AuthenticationManager authenticationManager, JWTGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
    }

    @GetMapping
    public String home() {
        return "Hello world!";
    }

    @GetMapping("/user")
    // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AuthResponseDto> user(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenGenerator.generateToken(authentication);
        System.out.println(token);
        return new ResponseEntity<>(new AuthResponseDto("Bearer " + token), HttpStatus.OK);
    }

    @GetMapping("/user/method1")
    @PreAuthorize("hasRole('USER')")
    public String userMethod() {
        return "Some user methods.";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Hello, Admin!";
    }

}
