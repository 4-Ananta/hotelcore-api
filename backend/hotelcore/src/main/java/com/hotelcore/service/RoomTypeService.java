package com.hotelcore.service;

import com.hotelcore.dto.RoomTypeRequest;
import com.hotelcore.dto.RoomTypeResponse;
import com.hotelcore.entity.RoomType;
import com.hotelcore.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomTypeResponse>findAll() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        List<RoomTypeResponse> hasil = new ArrayList<>();
        for (RoomType roomType : roomTypes) {
            RoomTypeResponse response = toResponse(roomType);
            hasil.add(response);
        }
        return hasil;
    }

    public RoomTypeResponse findById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room Type tidak ditemukan"));
        return toResponse(roomType);
    }

    public RoomTypeResponse saveRoomType(RoomTypeRequest request) {
        RoomType roomType = toEntity(request);
        RoomType saved = roomTypeRepository.save(roomType);
        return toResponse(saved);
    }

    public RoomTypeResponse update(Long id, RoomTypeRequest request) {
        RoomType existing = roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room Type tidak ditemukan"));

        existing.setName(request.getName());
        existing.setCapacity(request.getCapacity());
        existing.setDescription(request.getDescription());

        RoomType saved = roomTypeRepository.save(existing);

        return toResponse(saved);
    }


    public void delete(Long id) {
        roomTypeRepository.deleteById(id);
    }

    private RoomTypeResponse toResponse(RoomType roomType) {
        RoomTypeResponse response = new RoomTypeResponse();

        response.setId(roomType.getId());
        response.setName(roomType.getName());
        response.setCapacity(roomType.getCapacity());
        response.setDescription(roomType.getDescription());
        response.setCreatedAt(roomType.getCreatedAt());
        response.setUpdatedAt(roomType.getUpdatedAt());

        return response;
    }

    private RoomType toEntity(RoomTypeRequest request) {
        RoomType roomType = new RoomType();

        roomType.setName(request.getName());
        roomType.setCapacity(request.getCapacity());
        roomType.setDescription(request.getDescription());
        return roomType;
    }

}
