package com.codeup.blog.controllers;


import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UsersRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class PostsController {
    private final PostSvc postSvc;
    private final UsersRepository usersDao;

    @Autowired
    public PostsController(PostSvc postSvc, UsersRepository usersDao) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;
    }

    @GetMapping({"/posts", "/"})
    public String viewAllPosts(Model viewModel) {

        Iterable<Post> posts = postSvc.findAllPosts();

        viewModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model viewModel) {

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
    public String createPost(@Valid Post post, Errors validation, Model viewModel) {
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "/posts/create";
        }
        postSvc.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model viewModel, @ModelAttribute Post post) {
        viewModel.addAttribute("post", postSvc.findOnePost(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@Valid Post post, Errors validation, Model viewModel, @PathVariable long id) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (postSvc.findOnePost(id).getUser().getId() != (user.getId())) {
            return "redirect:/posts";
        }


        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "posts/edit";
        }


        post.setUser(user);
        postSvc.savePost(post);
        return "redirect:/posts";
    }

    @PostMapping("posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (postSvc.findOnePost(id).getUser().getId() != (user.getId())) {
            return "redirect:/posts";
        }

        Post post = postSvc.findOnePost(id);
        postSvc.deletePost(post);
        return "redirect:/posts";
    }

//    @GetMapping(/"search")
//    public String searchPost(@RequestParam String keyword) {
//
//    }
}