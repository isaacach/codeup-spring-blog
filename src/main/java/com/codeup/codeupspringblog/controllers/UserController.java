package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userDao;

    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String registerUser() {
        return ("/register");
    }

    @PostMapping("/register")
    public String createUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        User user = new User(username, email, password);
        userDao.save(user);
        return "redirect:/posts";
    }

    @GetMapping("/user/{id}/posts")
    public String userPosts(@PathVariable long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("userPosts", user.getPosts());
        return "/posts/user_posts";
    }
}
