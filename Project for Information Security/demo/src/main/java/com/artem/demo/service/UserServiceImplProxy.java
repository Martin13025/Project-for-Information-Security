package com.artem.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImplProxy implements UserService {

    private UserServiceImpl userService;

    @Override
    public void getUserById(long id) {
        System.out.println("Proxy Method!");
        userService.getUserById(id);
    }

    @Override
    public void getUserByUsername(String username) {
        System.out.println("Proxy Method!");
        userService.getUserByUsername(username);
    }
}
