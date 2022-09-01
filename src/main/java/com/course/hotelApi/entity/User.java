package com.course.hotelApi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please enter the username")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Please enter the password")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Please enter the role")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
