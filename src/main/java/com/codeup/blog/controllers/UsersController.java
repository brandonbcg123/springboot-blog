package com.codeup.blog.controllers;

import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
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
    public String registerUser(@Valid User user, Errors validation, Model viewModel) {

//    user.setPassword(passwordEncoder.encode(user.getPassword()));
//   place the hashing encoder to storing password in a variable

        User existingUser = repository.findByUsername(user.getUsername());

        User existingEmail = repository.findByEmail(user.getEmail());

        if (existingUser != null) {
            validation.rejectValue(
                    "username",
                    "user.username",
                    "Username already taken"
            );
        }
        if (existingEmail != null) {
            validation.rejectValue(
                    "email",
                    "user.email",
                    "Email already taken!"
            );
        }


        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/registration";
        }


        String hashPassword = passwordEncoder.encode(user.getPassword());


        user.setPassword(hashPassword);
        repository.save(user);
        return "redirect:/login";
    }
}
