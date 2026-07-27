package com.hotelcore.dto;

import com.hotelcore.enums.Gender;
import com.hotelcore.enums.IdentityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestRequest {

    @NotBlank(message = "Identity Number tidak boleh kosong")
    private String identityNumber;

    @NotNull(message = "Identity Type tidak boleh kosong")
    private IdentityType identityType;

    @NotBlank(message = "Full Name tidak boleh kosong")
    private String fullName;

    @NotNull(message = "Gender tidak boleh kosong")
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private Boolean isBlacklisted;


}
