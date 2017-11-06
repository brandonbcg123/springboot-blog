package com.codeup.blog.controllers;


import com.codeup.blog.models.Post;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        List<Post> posts = postSvc.findAllPosts();

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
    public String viewCreatePostForm() {
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createAPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        postSvc.savePost(post);
            return "redirect:/posts";
        }

}
