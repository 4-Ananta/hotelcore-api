package com.hotelcore.service;

import com.hotelcore.dto.RoomRequest;
import com.hotelcore.dto.RoomResponse;
import com.hotelcore.entity.Room;
import com.hotelcore.entity.RoomType;
import com.hotelcore.enums.RoomStatus;
import com.hotelcore.repository.RoomRepository;
import com.hotelcore.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    public RoomService(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    private RoomType findRoomTypeByName(String name) {
        return roomTypeRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("Room Type tidak ditemukan"));
    }


    public List<RoomResponse> findAll() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomResponse> hasil = new ArrayList<>();
        for (Room room : rooms) {
            RoomResponse response = toResponse(room);
            hasil.add(response);
        }
        return hasil;
    }

    public RoomResponse findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room tidak ditemukan"));
        return toResponse(room);
    }

    public RoomResponse saveRoom(RoomRequest request) {
        Room room = toEntity(request);
        Room saved = roomRepository.save(room);
        return toResponse(saved);
    }

    public RoomResponse update(Long id, RoomRequest request) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room tidak ditemukan"));

        RoomType roomType = findRoomTypeByName(request.getRoomTypeName());

        existing.setRoomNumber(request.getRoomNumber());
        existing.setFloor(request.getFloor());
        existing.setStatus(request.getStatus());
        existing.setRoomType(roomType);

        Room saved = roomRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {roomRepository.deleteById(id);}

    private RoomResponse toResponse(Room room){
        RoomResponse response = new RoomResponse();

        response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setFloor(room.getFloor());
        response.setRoomTypeName(room.getRoomType().getName());
        response.setRoomTypeCapacity(room.getRoomType().getCapacity());
        response.setRoomTypeDescription(room.getRoomType().getDescription());
        response.setStatus(room.getStatus());
        response.setCreatedAt(room.getCreatedAt());
        response.setUpdatedAt(room.getUpdatedAt());

        return response;
    }

    private Room toEntity(RoomRequest request){
        RoomType roomType = findRoomTypeByName(request.getRoomTypeName());

        Room room = new Room();

        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(roomType);
        room.setFloor(request.getFloor());
        room.setStatus(request.getStatus());

        return room;

    }
}
