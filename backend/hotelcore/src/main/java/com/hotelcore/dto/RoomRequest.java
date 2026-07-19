package com.hotelcore.dto;

import com.hotelcore.enums.RoomStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {
    private String roomNumber;
    private Long roomTypeId;
    private Integer floor;
    private RoomStatus status;
}
