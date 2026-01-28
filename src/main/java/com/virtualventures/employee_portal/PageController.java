package com.virtualventures.employee_portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/login.html";
    }

    @GetMapping("/attendance")
    public String attendance() {
        return "redirect:/attendance.html";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "redirect:/2index.html";
    }

    @GetMapping("/register")
    public String register() {
        return "redirect:/index.html";
    }

}
