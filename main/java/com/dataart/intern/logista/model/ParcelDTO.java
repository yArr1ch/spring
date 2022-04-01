package com.dataart.intern.logista.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelDTO {

    private Integer id;
    private Client sender;
    private Client receiver;
    private DepotDTO origin;
    private DepotDTO destination;
    private String description;
    private Integer width;
    private Integer height;
    private Integer length;
    private Integer insuredPrice;
    private Integer price;
}
