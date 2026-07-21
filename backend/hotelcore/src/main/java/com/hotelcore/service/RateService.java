package com.hotelcore.service;

import com.hotelcore.dto.RateRequest;
import com.hotelcore.dto.RateResponse;
import com.hotelcore.entity.Rate;
import com.hotelcore.entity.RoomType;
import com.hotelcore.repository.RateRepository;
import com.hotelcore.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateService {
    private final RateRepository rateRepository;
    private final RoomTypeRepository roomTypeRepository;

    public  RateService(RateRepository rateRepository,RoomTypeRepository roomTypeRepository) {
        this.rateRepository = rateRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    private RoomType findRoomTypeByName(String name) {
        return roomTypeRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("Room Type tidak ditemukan"));
    }

    public List<RateResponse> findAll() {
        List<Rate> rates = rateRepository.findAll();
        List<RateResponse> hasil = new ArrayList<>();
        for (Rate rate : rates) {
            RateResponse response = toResponse(rate);
            hasil.add(response);
        }
        return hasil;
    }

    public  RateResponse findById(Long id) {
        Rate rate = rateRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Rate tidak ditemukan"));
        return toResponse(rate);
    }

    public RateResponse saveRate(RateRequest request) {
        Rate rate = toEntity(request);
        Rate saved =rateRepository.save(rate);
        return toResponse(saved);
    }

    public RateResponse update(Long id, RateRequest request) {
        Rate existing = rateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rate tidak ditemukan"));
        RoomType roomType = findRoomTypeByName(request.getRoomTypeName());

        existing.setRoomType(roomType);
        existing.setRateName(request.getRateName());
        existing.setPrice(request.getPrice());
        existing.setDescription(request.getDescription());
        existing.setIsActive(request.getIsActive());
        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());

        Rate saved = rateRepository.save(existing);
        return toResponse(saved);
    }

    public void deleted (Long id) {
        rateRepository.deleteById(id);
    }

    private RateResponse toResponse(Rate rate) {
        RateResponse response = new RateResponse();

        response.setId(rate.getId());
        response.setRoomTypeName(rate.getRoomType().getName());
        response.setRateName(rate.getRateName());
        response.setPrice(rate.getPrice());
        response.setDescription(rate.getDescription());
        response.setIsActive(rate.getIsActive());
        response.setStartDate(rate.getStartDate());
        response.setEndDate(rate.getEndDate());
        response.setCreatedAt(rate.getCreatedAt());
        response.setUpdatedAt(rate.getUpdatedAt());

        return response;
    }

    private Rate toEntity(RateRequest request){
       RoomType roomType = findRoomTypeByName(request.getRoomTypeName());
        Rate rate = new Rate();

        rate.setRoomType(roomType);
        rate.setRateName(request.getRateName());
        rate.setPrice(request.getPrice());
        rate.setDescription(request.getDescription());
        rate.setIsActive(request.getIsActive());
        rate.setStartDate(request.getStartDate());
        rate.setEndDate(request.getEndDate());

        return rate;

    }

}
