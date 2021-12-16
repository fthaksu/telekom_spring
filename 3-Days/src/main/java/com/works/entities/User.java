package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "users_uid", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_rid", referencedColumnName = "rid"))
    private List<Role> roles;
}
