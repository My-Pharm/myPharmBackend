package myPharm.myPharm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/success")
    public String loginSuccess(@RequestParam String accessToken, @RequestParam String refreshToken, Model model) {
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("refreshToken", refreshToken);
        return "loginSuccess";
    }
}

