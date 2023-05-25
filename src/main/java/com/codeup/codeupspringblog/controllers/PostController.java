package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(Model model) {
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post(2, "This is the title", "This is the body");
        Post post2 = new Post(3, "Volcano eruption", "Mount Fuji has erupted");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);
        return "/posts/show";
    }

    @GetMapping("/posts/{id}")
    public String onePost(@PathVariable int id, Model model) {
        Post post = new Post(1, "title", "body");
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm() {
        return "view the form for creating a post";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

}
