package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class City extends Base {

    @Column(name = "name")
    @NotEmpty(message = "Please enter the name of city")
    private String name;
}
