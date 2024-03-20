package com.artem.demo.service;

import org.springframework.stereotype.Service;
import com.artem.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void getUserById(long id) {
        userRepository.findById(id);
    }

    @Override
    public void getUserByUsername(String username) {
        userRepository.findByUsername(username);
    }
}
