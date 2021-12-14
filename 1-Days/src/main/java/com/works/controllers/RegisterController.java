package com.works.controllers;

import com.works.entities.User;
import com.works.repositories.UserRepository;
import com.works.utils.Control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {

    List<String> errors = new ArrayList<>();
    final UserRepository uRepo;
    String token = "";
    final Control control;
    public RegisterController(UserRepository uRepo, Control control) {
        this.uRepo = uRepo;
        this.control = control;
    }

    @GetMapping("/register")
    public String register(Model model) {
        token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        if ( errors.size() > 0 ) {
            model.addAttribute("errors", errors);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerForm(@Valid User user, BindingResult result, @RequestParam String token, Model model) {
        if ( this.token.equals(token) ) {
            errors.clear();
            if ( result.hasErrors() ) {
                result.getAllErrors().forEach( item -> {
                    errors.add( item.getDefaultMessage() );
                });
                model.addAttribute("errors", errors);
                model.addAttribute("user", user);
                return "register";
            }else {
                Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(user.getEmail());
                if (oUser.isPresent() ) {
                    errors.add("Bu kullanıcı daha önceden kayıtlı");
                }else{
                    user.setPassword( control.fncMd5(user.getPassword(), 3) );
                    User uUser = uRepo.save(user);
                    if ( uUser != null) {
                        return "redirect:/login";
                    }
                }
            }
        }
        return "redirect:/register";
    }

}
