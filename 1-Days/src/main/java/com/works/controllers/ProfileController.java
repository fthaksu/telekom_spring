package com.works.controllers;

import com.works.utils.Control;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    final Control control;
    public ProfileController(Control control) {
        this.control = control;
    }

    @GetMapping("/profile")
    public String dashboard() {
        return control.control("profile");
    }

}
