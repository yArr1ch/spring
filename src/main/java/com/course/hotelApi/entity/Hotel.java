package com.course.hotelApi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please enter the name of hotel")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Please specify pools")
    @Column(name = "pools")
    @Enumerated(EnumType.STRING)
    private Available pools;

    @NotNull(message = "Please specify water slides")
    @Column(name = "water_slides")
    @Enumerated(EnumType.STRING)
    private Available waterSlides;

    @NotNull(message = "Please specify tennis court")
    @Column(name = "tennis_court")
    @Enumerated(EnumType.STRING)
    private Available tennisCourt;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Room> rooms;
}
