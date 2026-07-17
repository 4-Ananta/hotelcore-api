package com.hotelcore.controller;

import com.hotelcore.dto.RoomTypeRequest;
import com.hotelcore.dto.RoomTypeResponse;
import com.hotelcore.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomTypeResponse> getAllRoomType() {
        return roomTypeService.findAll();
    }

    @GetMapping("/{id}")
    public RoomTypeResponse getRoomTypeById(@PathVariable Long id) {
        return roomTypeService.findById(id);
    }

    @PostMapping
    public RoomTypeResponse createRoomType(@RequestBody RoomTypeRequest request) {
        return roomTypeService.saveRoomType(request);
    }

    @PutMapping("/{id}")
    public RoomTypeResponse updateRoomType(@PathVariable Long id,
                                   @RequestBody RoomTypeRequest request) {
        return roomTypeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletedRoomType(@PathVariable Long id) {
        roomTypeService.delete(id);
    }
}
