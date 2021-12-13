package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(length = 70)
    @NotNull(message = "nameSurname NotNull")
    @NotEmpty(message = "nameSurname NotEmpty")
    @Length(min = 2, max = 70, message = "nameSurname min = 2, max = 70")
    private String nameSurname;

    @Column(length = 200, unique = true)
    @NotNull(message = "email NotNull")
    @NotEmpty(message = "email NotEmpty")
    @Email(message = "email format fail")
    private String email;

    @NotNull(message = "email NotNull")
    @NotEmpty(message = "email NotEmpty")
    private String password;

    @Min(value = 2019, message = "year min 2019")
    @Max(value = 2021, message = "year max 2021")
    private Short year;

    @Min(value = 1, message = "group min 1")
    @Max(value = 3, message = "group max 3")
    private Byte group;

}
