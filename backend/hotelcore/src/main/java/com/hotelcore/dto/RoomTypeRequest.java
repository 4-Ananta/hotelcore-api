package com.hotelcore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeRequest {
    private String name;
    private Integer capacity;
    private String description;
}
