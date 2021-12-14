package com.works.controllers;

import com.works.utils.Control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    final Control control;
    public DashboardController(Control control) {
        this.control = control;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return control.control("dashboard");
    }

}
