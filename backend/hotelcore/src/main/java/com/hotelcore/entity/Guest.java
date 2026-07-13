package com.hotelcore.entity;

import com.hotelcore.enums.Gender;
import com.hotelcore.enums.IdentityType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity_number", nullable = false, length = 50)
    private String identityNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "identity_type", nullable = false)
    private IdentityType identityType;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 100)
    private String nationality;

    @Column(name = "is_blacklisted")
    private Boolean isBlacklisted;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
    
}
