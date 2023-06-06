package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerUser() {
        return ("/register");
    }

    @PostMapping("/register")
    public String createUser(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ) {
        password = passwordEncoder.encode(password);
        User user = new User(username, email, password);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{id}/posts")
    public String userPosts(@PathVariable long id, Model model) {
        User user = userDao.findUserById(id);
        model.addAttribute("userPosts", user.getPosts());
        return "/posts/user_posts";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = userDao.findUserById(userId);
        model.addAttribute("user", user);
        return ("/profile");
    }

    @PostMapping("/profile")
    public String changeProfile(@RequestParam(name = "email") String email) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = userDao.findUserById(userId);
        user.setEmail(email);
        userDao.save(user);
        return "redirect:/profile";
    }


}
