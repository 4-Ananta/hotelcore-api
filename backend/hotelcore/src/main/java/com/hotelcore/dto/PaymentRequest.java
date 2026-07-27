package com.hotelcore.dto;

import com.hotelcore.enums.PaymentMethod;
import com.hotelcore.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {

    @NotNull(message = "Reservation ID tidak boleh kosong")
    private Long reservation;

    @NotNull(message = "Amount tidak boleh kosong")
    private BigDecimal amount;

    @NotNull(message = "Payment Method tidak boleh kosong")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment Status tidak boleh kosong")
    private PaymentStatus paymentStatus;

    private String referenceNumber;

    @NotBlank(message = "Created By tidak boleh kosong")
    private String createdBy;
}
