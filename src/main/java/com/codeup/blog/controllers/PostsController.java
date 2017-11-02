package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class PostsController {

    @ResponseBody
    @GetMapping("/posts")
    public String indexPage() {
        return "Index Page";
    }

    @ResponseBody
    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id) {
        return "Post Id " + id + ": Post!";
    }

    @ResponseBody
    @GetMapping("/posts/create")
        public String viewCreatePostForm() {
            return "Here is the form to create a post!!!";
        }

    @ResponseBody
    @PostMapping("/posts/create")
    public String createAPost() {
    return "Create a new Post!";
    }
}