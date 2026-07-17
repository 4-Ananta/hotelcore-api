package com.hotelcore.dto;

import com.hotelcore.enums.Gender;
import com.hotelcore.enums.IdentityType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GuestResponse {

    private Long id;

    private String identityNumber;
    private IdentityType identityType;
    private String fullName;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private Boolean isBlacklisted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}