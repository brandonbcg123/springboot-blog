package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Long>{
    @Query(value = "SELECT * FROM posts p WHERE " + "LOWER(p.title) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " + "LOWER(p.body) LIKE LOWER(CONCAT('%',:keyword, '%'))",
    nativeQuery = true)
    List<Post> search(@Param("keyword") String keyword);
}

//    @Query("SELECT p FROM Post p WHERE p.title LIKE ?1 or p.body LIKE ?1")
