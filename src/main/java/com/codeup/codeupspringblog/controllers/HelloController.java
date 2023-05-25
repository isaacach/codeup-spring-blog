package com.codeup.codeupspringblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}/and/{name2}")
    @ResponseBody
    public String sayHelloTwo(@PathVariable String name, @PathVariable String name2) {
        return "hello, " + name + " and " + name2 + "!";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
