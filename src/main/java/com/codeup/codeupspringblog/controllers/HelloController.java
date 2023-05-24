package com.codeup.codeupspringblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHelloOne(@PathVariable String name) {
        return "hello, " + name + "!";
    }

    @GetMapping("/hello/{name}/and/{name2}")
    @ResponseBody
    public String sayHello(@PathVariable String name, @PathVariable String name2) {
        return "hello, " + name + " and " + name2 + "!";
    }

    @RequestMapping(path ="/hello/{name}", method = RequestMethod.POST)
    @ResponseBody
    public String sayHelloAgain(@PathVariable String name) {
        return "hello, " + name + "!";
    }
}
