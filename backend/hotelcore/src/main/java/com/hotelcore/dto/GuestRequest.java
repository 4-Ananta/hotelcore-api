package com.hotelcore.dto;

import com.hotelcore.enums.Gender;
import com.hotelcore.enums.IdentityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestRequest {

    private String identityNumber;
    private IdentityType identityType;
    private String fullName;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private Boolean isBlacklisted;


}
