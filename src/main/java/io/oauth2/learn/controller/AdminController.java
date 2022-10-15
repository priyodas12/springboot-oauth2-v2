package io.oauth2.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AdminController {

    @GetMapping("/admin/{name}")
    public String adminPageController(@PathVariable String name) {
        return "Hey, this page is build for Admin : " + name;
    }
}
