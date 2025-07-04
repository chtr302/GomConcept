package com.gomconcept.gomconcept.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    
    @GetMapping("/tutorials")
    public String tutorials() {
        return "tutorials";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}