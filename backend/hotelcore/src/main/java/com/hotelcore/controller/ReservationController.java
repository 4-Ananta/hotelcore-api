package com.hotelcore.controller;

import com.hotelcore.dto.ReservationRequest;
import com.hotelcore.dto.ReservationResponse;
import com.hotelcore.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<ReservationResponse> getAllReservation() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservationById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public ReservationResponse createReservation(@RequestBody ReservationRequest request) {
        return reservationService.saveReservation(request);
    }

    @PutMapping("/{id}")
    public ReservationResponse updateReservation(@PathVariable Long id,
                                          @RequestBody ReservationRequest request) {
        return reservationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
    }
}
