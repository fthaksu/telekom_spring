package com.works.controllers;

import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {

    List<String> errors = new ArrayList<>();
    final UserRepository uRepo;
    String token = "";
    public RegisterController(UserRepository uRepo) {
        this.uRepo = uRepo;
    }

    @GetMapping("/")
    public String register(Model model) {
        token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        if ( errors.size() > 0 ) {
            model.addAttribute("errors", errors);
            errors.clear();
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(User user, @RequestParam String token) {
        if ( this.token.equals(token) ) {
            errors.clear();
            Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(user.getEmail());
            if (oUser.isPresent() ) {
                errors.add("Bu kullanıcı daha önceden kayıtlı");
            }else{
                uRepo.save(user);
            }
        }
        return "redirect:/";
    }

}
