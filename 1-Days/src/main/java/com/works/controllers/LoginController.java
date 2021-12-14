package com.works.controllers;

import com.works.entities.User;
import com.works.utils.Control;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    final Control control;
    public LoginController(Control control) {
        this.control = control;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String loginForm(User user, @RequestParam(defaultValue = "") String remember) {
        boolean status = control.login(user, remember);
        String page = status == true ? "redirect:/dashboard": "redirect:/";
        return page;
    }


}
