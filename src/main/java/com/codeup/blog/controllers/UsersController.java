package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


public class UsersController {
    private UsersRepository repository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UsersController(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/registration";
    }

    @PostMapping("/register")
    public String showRegistrationForm(@ModelAttribute User user) {

//    user.setPassword(passwordEncoder.encode(user.getPassword()));
//   place the hashing encoder to storing password in a variable

        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        repository.save(user);
        return "redirect:users/login";
    }
}
