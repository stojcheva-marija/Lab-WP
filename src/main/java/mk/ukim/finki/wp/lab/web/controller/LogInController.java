package mk.ukim.finki.wp.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LogInController {
    @GetMapping
    public String getLogIn(){return "login";}
    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        return "login";
    }
}
