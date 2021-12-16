package com.works.restcontrollers;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;

import com.works.services.UserDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NewUserRestController {

    final RoleRepository rRepo;
    final UserDetailService service;
    public NewUserRestController(RoleRepository rRepo, UserDetailService service) {
        this.rRepo = rRepo;
        this.service = service;
    }

    @GetMapping("/new")
    public String newUser() {

        List<Role> roles = new ArrayList<>();
        //Optional<Role> role1 = rRepo.findById(1);
        Optional<Role> role2 = rRepo.findById(2);
        //roles.add(role1.get());
        roles.add(role2.get());

        User u = new User();
        u.setEmail("veli@mail.com");
        u.setName("Veli");
        u.setSurname("Bilmem");
        u.setRoles(roles);
        u.setPassword("12345");
        u.setEnabled(true);
        u.setTokenExpired(true);

        service.register(u);

        return "";
    }


}
