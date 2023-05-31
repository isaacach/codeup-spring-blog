package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public User randomUser(UserRepository userDao) {
        List<User> allUsers = userDao.findAll();
        int randomInt = new Random().nextInt(allUsers.size());
        return allUsers.get(randomInt);
    }

    @GetMapping("/posts")
    public String allPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String onePost(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String postForm() {
        return "/posts/create";
    }

    @RequestMapping(path="/posts/create", method = RequestMethod.POST)
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post post = new Post(title, body, randomUser(userDao));
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
