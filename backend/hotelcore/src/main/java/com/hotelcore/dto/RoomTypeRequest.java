package com.hotelcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeRequest {

    @NotBlank(message = "Name tidak boleh kosong")
    private String name;

    @NotNull(message = "Capacity tidak boleh kosong")
    private Integer capacity;
    private String description;
}
