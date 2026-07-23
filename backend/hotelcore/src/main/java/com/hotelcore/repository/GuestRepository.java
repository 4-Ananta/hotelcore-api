package com.hotelcore.repository;

import com.hotelcore.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findByIdentityNumber(String identityNumber);
    Optional<Guest> findByFullName(String fullName);
}
