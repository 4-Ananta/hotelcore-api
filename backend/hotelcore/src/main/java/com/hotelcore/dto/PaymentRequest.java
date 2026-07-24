package com.hotelcore.dto;

import com.hotelcore.enums.PaymentMethod;
import com.hotelcore.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {
    private Long reservation;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String referenceNumber;
    private String createdBy;
}
