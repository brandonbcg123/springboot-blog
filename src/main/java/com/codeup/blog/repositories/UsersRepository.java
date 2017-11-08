package com.codeup.blog.repositories;

import com.codeup.blog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}


