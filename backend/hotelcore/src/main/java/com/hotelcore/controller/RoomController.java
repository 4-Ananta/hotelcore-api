package com.hotelcore.controller;

import com.hotelcore.dto.RoomRequest;
import com.hotelcore.dto.RoomResponse;
import com.hotelcore.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomResponse> getAllRoom(){
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public RoomResponse getRoomById(@PathVariable Long id) {
        return roomService.findById(id);
    }

    @PostMapping
    public RoomResponse createRoom(@RequestBody RoomRequest request){
        return roomService.saveRoom(request);
    }

    @PutMapping("/{id}")
    public RoomResponse updateRoom(@PathVariable Long id,
                                   @RequestBody RoomRequest request){
        return roomService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
    }

}
