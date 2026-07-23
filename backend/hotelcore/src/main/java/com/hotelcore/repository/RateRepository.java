package com.hotelcore.repository;

import com.hotelcore.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByRateName(String rateName);

}
