package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final CommentRepository commentDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, CommentRepository commentDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.emailService = emailService;
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
    public String postForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping(path="/posts/create")
    public String createPost(@ModelAttribute Post post) {
        post.setUser(randomUser(userDao));
        postDao.save(post);
        emailService.prepareAndSend(post, "New Post Created", "Your new post has been posted", "isaac.achenbach1@gmail.com");
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

    @PostMapping("/posts/comment")
    public String submitComment(@RequestParam(name = "content") String content, @RequestParam(name = "postId") long postId) {
        Post post = postDao.findById(postId);
        Comment comment = new Comment(content, post);
        commentDao.save(comment);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post post = postDao.findById(id);
        model.addAttribute("post", post);
        return "redirect:/posts/create";
    }

    @PostMapping(path="/posts/{id}/edit\"")
    public String submitEdit(@ModelAttribute Post post) {
        post.setUser(randomUser(userDao));
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/restposts")
    public List<Post> getRestPosts() {
        return postDao.findAll();
    }



}
