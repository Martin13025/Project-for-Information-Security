package com.artem.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.artem.demo.dto.UserDTO;
import com.artem.demo.entity.User;
import com.artem.demo.repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/{username}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createUserDTO")
    public ResponseEntity<UserDTO> createUserDTO(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setUsername(savedUser.getUsername());
        return ResponseEntity.ok(savedUserDTO);
    }
}
