package com.hotelcore.dto;

import com.hotelcore.enums.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {

    @NotBlank(message = "Room Number tidak boleh kosong")
    private String roomNumber;

    @NotBlank(message = "Room Type Name tidak boleh kosong")
    private String roomTypeName;

    @NotNull(message = "Floor tidak boleh kosong")
    private Integer floor;

    @NotNull(message = "Status tidak boleh kosong")
    private RoomStatus status;
}
