package com.hotelcore.dto;

import com.hotelcore.enums.RoomStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomResponse {
    private Long id;
    private String roomNumber;
    private String roomTypeName;
    private Integer roomTypeCapacity;
    private String roomTypeDescription;
    private Integer floor;
    private RoomStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
