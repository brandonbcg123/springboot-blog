package com.codeup.blog.services;

import com.codeup.blog.models.User;
import com.codeup.blog.models.UsersWithRoles;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository repository;


    public UserDetailsLoader(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UsersWithRoles(user, Collections.emptyList());
    }
}
