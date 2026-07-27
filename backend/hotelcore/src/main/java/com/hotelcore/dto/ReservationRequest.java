package com.hotelcore.dto;

import com.hotelcore.enums.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {

    @NotBlank(message = "Identity Number tidak boleh kosong")
    private String identityNumber;

    @NotBlank(message = "Room Number tidak boleh kosong")
    private String roomNumber;

    @NotBlank(message = "Rate Name tidak boleh kosong")
    private String rateName;

    @NotBlank(message = "Created By tidak boleh kosong")
    private String createdBy;

    @NotNull(message = "Check In Date tidak boleh kosong")
    private LocalDate checkInDate;

    @NotNull(message = "Check Out Date tidak boleh kosong")
    private LocalDate checkOutDate;

    @NotNull(message = "Status tidak boleh kosong")
    private ReservationStatus status;
}
