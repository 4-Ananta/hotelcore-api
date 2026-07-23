package com.hotelcore.dto;

import com.hotelcore.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {
    private String identityNumber;
    private String roomNumber;
    private String rateName;
    private String createdBy;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
}
