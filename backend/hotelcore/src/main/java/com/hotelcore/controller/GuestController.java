package com.hotelcore.controller;

import com.hotelcore.dto.GuestRequest;
import com.hotelcore.dto.GuestResponse;
import com.hotelcore.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<GuestResponse> getAllGuest() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public GuestResponse getGuestById(@PathVariable Long id){
            return guestService.findById(id);
    }

    @PostMapping
    public GuestResponse createGuest(@RequestBody GuestRequest request) {
        return guestService.saveGuest(request);
    }

    @PutMapping("/{id}")
    public GuestResponse updateGuest(@PathVariable Long id,
                             @RequestBody GuestRequest request) {
        return guestService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.delete(id);
    }
}
