package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller

public class PostsController {


    @GetMapping("/posts")
    public String indexPage(Model viewModel) {
        ArrayList<Post> posts = new ArrayList<>();

        Post postOne = new Post("My Coding Experience", "Learning 8 different languages is tough regardless if they are languages that we speak or languages that we code. Learning code is something beautiful, but it can be frustrating at the same time. Overall there is nothing more gratifying than solving a problem that took hours, days, or even weeks to solve! Once I solve a problem I feel the world is mine!!!");

        Post postTwo = new Post("Kawhi Leonard's Injury", "I miss Kawhi Leonard!!! Please get healthy and come back as soon as possible amigo!!! The city of San Antonio need you!!!");

        posts.add(postOne);
        posts.add(postTwo);

        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id, Model viewModel) {
        Post post= new Post("My Coding Experience", "Learning 8 different languages is tough regardless if they are languages that we speak or languages that we code. Learning code is something beautiful, but it can be frustrating at the same time. Overall there is nothing more gratifying than solving a problem that took hours, days, or even weeks to solve! Once I solve a problem I feel the world is mine!!!");

        viewModel.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
        public String viewCreatePostForm() {
            return "Here is the form to create a post!!!";
        }

    @PostMapping("/posts/create")
    public String createAPost() {
    return "Create a new Post!";
    }
}