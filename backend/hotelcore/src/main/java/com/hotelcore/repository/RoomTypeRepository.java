package com.hotelcore.repository;

import com.hotelcore.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>{
    Optional<RoomType> findByName(String roomTypeName);
}
