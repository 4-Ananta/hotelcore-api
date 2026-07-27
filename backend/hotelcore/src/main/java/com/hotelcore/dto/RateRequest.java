package com.hotelcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class RateRequest {

    @NotBlank(message = "Room Type name tidak boleh kosong")
    private String roomTypeName;

    @NotBlank(message = "Rate Namme tidak boleh kosong")
    private String rateName;

    @NotNull(message = "Price tidak boleh kosong")
    private BigDecimal price;
    private String description;

    @NotNull(message = "Is Active tidak boleh kosong")
    private Boolean isActive;

    @NotNull(message = "Start Date tidak boleh kosong")
    private LocalDateTime startDate;

    @NotNull(message = "End Date tidak boleh kosong")
    private LocalDateTime endDate;
}
