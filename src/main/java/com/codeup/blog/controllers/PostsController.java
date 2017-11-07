package com.codeup.blog.controllers;


import com.codeup.blog.models.Post;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class PostsController {
    private final PostSvc postSvc;

    @Autowired
    public PostsController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }


    @GetMapping("/posts")
    public String viewAllAdds(Model viewModel) {

        Iterable <Post> posts = postSvc.findAllPosts();

        viewModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id, Model viewModel) {

        Post post = postSvc.findOnePost(id);

        viewModel.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePostForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createAPost(@ModelAttribute Post post) {
        postSvc.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editAPost(@PathVariable int id, Model viewModel) {
        viewModel.addAttribute("post", postSvc.findOnePost(id));
        return "/posts/edit";
    }
}