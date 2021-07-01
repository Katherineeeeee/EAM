package com.rookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 主页的Controller
 */
@Controller
@RequestMapping("/")
public class HomeController {


    //返回主页
    @GetMapping
    public String home() {
        return "index";
    }

    //返回404
    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}
