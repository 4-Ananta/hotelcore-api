package com.hotelcore.dto;

import com.hotelcore.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ReservationResponse {
    private Long id;
    private String identityNumber;
    private String fullName;
    private String roomNumber;
    private String rateName;
    private String createdBy;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer numberOfNights;
    private BigDecimal pricePerNight;
    private BigDecimal totalAmount;
    private ReservationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
