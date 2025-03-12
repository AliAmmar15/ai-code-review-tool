package com.yourteamname.aipoweredcodereview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index"); // Ensures it loads index.html properly
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index"); // Explicitly maps "/index" to index.html
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login"); // Loads login.html
    }
}
