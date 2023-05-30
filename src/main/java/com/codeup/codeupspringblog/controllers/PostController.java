package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String onePost(@PathVariable int id, Model model) {
        return "/posts/index";
    }

    @GetMapping("/posts/create")
    public String postForm() {
        return "/posts/create";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    public String searchPosts() {
        return "posts/search";
    }

    @PostMapping("/posts/search")
    public String searchResults(@RequestParam(name="title") String title, Model model) {
        model.addAttribute("results", postDao.findByTitle(title));
        return "posts/search";
    }

}
