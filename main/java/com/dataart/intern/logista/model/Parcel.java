package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Parcel {

    private Integer id;

    @NotNull(message = "Please enter the sender")
    private Integer senderId;

    @NotNull(message = "Please enter the receiver")
    private Integer receiverId;

    @NotNull(message = "Please enter the origin")
    private Integer originId;

    @NotNull(message = "Please enter the destination")
    private Integer destinationId;

    @NotEmpty(message = "Please enter the description")
    @Size(min = 1, max = 20)
    private String description;

    @NotNull(message = "Please enter the width")
    private Integer width;

    @NotNull(message = "Please enter the height")
    private Integer height;

    @NotNull(message = "Please enter the length")
    private Integer length;

    @NotNull(message = "Please enter the insurance price")
    private Integer insuredPrice;

    private Integer price;
}
