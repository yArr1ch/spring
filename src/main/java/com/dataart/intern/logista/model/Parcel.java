package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "parcels")
public class Parcel extends Base {

    @Column(name = "sender_id")
    @NotNull(message = "Please enter the sender")
    private Integer senderId;

    @Column(name = "receiver_id")
    @NotNull(message = "Please enter the receiver")
    private Integer receiverId;

    @Column(name = "origin_id")
    @NotNull(message = "Please enter the origin")
    private Integer originId;

    @Column(name = "destination_id")
    @NotNull(message = "Please enter the destination")
    private Integer destinationId;

    @Column(name = "description")
    @NotEmpty(message = "Please enter the description")
    @Size(min = 1, max = 20)
    private String description;

    @Column(name = "width")
    @NotNull(message = "Please enter the width")
    private Integer width;

    @Column(name = "height")
    @NotNull(message = "Please enter the height")
    private Integer height;

    @Column(name = "length")
    @NotNull(message = "Please enter the length")
    private Integer length;

    @Column(name = "insured_price")
    @NotNull(message = "Please enter the insurance price")
    private Integer insuredPrice;

    @Column(name = "price")
    private Integer price;
}
