package com.hotelcore.service;

import com.hotelcore.dto.ReservationRequest;
import com.hotelcore.dto.ReservationResponse;
import com.hotelcore.entity.*;
import com.hotelcore.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final RateRepository rateRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository,
                              GuestRepository guestRepository, RateRepository rateRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;
    }

    private Room findByRoomNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(()-> new RuntimeException("Room tidak ditemukan"));
    }

    private Guest findByIdentityNumber(String identityNumber){
        return guestRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(()-> new RuntimeException("Guest tidak ditemukan"));
    }

    private Rate findByRateName(String rateName) {
        return rateRepository.findByRateName(rateName)
                .orElseThrow(()-> new RuntimeException("Rate tidak ditemukan"));
    }

    private User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User tidak ditemukan"));
    }

    public List<ReservationResponse> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> hasil = new ArrayList<>();
        for(Reservation reservation : reservations) {
            ReservationResponse response = toResponse(reservation);
            hasil.add(response);
        }
        return hasil;
    }

    public ReservationResponse findById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation tidak ditemukan"));
        return toResponse(reservation);
    }

    public ReservationResponse saveReservation(ReservationRequest request){
        Reservation reservation = toEntity(request);
        Reservation saved = reservationRepository.save(reservation);
        return toResponse(saved);
    }

    public ReservationResponse update(Long id, ReservationRequest request) {
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation tidak ditemukan"));

        Guest guest = findByIdentityNumber(request.getIdentityNumber());
        Room room = findByRoomNumber(request.getRoomNumber());
        Rate rate = findByRateName(request.getRateName());
        User user = findByUsername(request.getCreatedBy());

        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())){
            throw new RuntimeException("Check out date harus setelah check in date");
        }
        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal pricePerNight = rate.getPrice();
        BigDecimal totalAmount = pricePerNight.multiply(BigDecimal.valueOf(nights));


        existing.setGuest(guest);
        existing.setRoom(room);
        existing.setRate(rate);
        existing.setCreatedBy(user);
        existing.setCheckInDate(request.getCheckInDate());
        existing.setCheckOutDate(request.getCheckOutDate());
        existing.setStatus(request.getStatus());
        existing.setNumberOfNights((int)nights);
        existing.setPricePerNight(pricePerNight);
        existing.setTotalAmount(totalAmount);

        Reservation saved = reservationRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationResponse toResponse(Reservation reservation) {
        ReservationResponse response = new ReservationResponse();

        response.setId(reservation.getId());
        response.setIdentityNumber(reservation.getGuest().getIdentityNumber());
        response.setFullName(reservation.getGuest().getFullName());
        response.setRoomNumber(reservation.getRoom().getRoomNumber());
        response.setRateName(reservation.getRate().getRateName());
        response.setCreatedBy(reservation.getCreatedBy().getFullName());
        response.setCheckInDate(reservation.getCheckInDate());
        response.setCheckOutDate(reservation.getCheckOutDate());
        response.setNumberOfNights(reservation.getNumberOfNights());
        response.setPricePerNight(reservation.getPricePerNight());
        response.setTotalAmount(reservation.getTotalAmount());
        response.setStatus(reservation.getStatus());
        response.setCreatedAt(reservation.getCreatedAt());
        response.setUpdatedAt(reservation.getUpdatedAt());

        return response;
    }

    private Reservation toEntity(ReservationRequest request){
        Guest guest = findByIdentityNumber(request.getIdentityNumber());
        Room room = findByRoomNumber(request.getRoomNumber());
        Rate rate = findByRateName(request.getRateName());
        User user = findByUsername(request.getCreatedBy());

        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())){
            throw new RuntimeException("Check out date harus setelah check in date");
        }
        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal pricePerNight = rate.getPrice();
        BigDecimal totalAmount = pricePerNight.multiply(BigDecimal.valueOf(nights));


        Reservation reservation = new Reservation();

        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setRate(rate);
        reservation.setCreatedBy(user);
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNumberOfNights((int)nights);
        reservation.setPricePerNight(pricePerNight);
        reservation.setTotalAmount(totalAmount);

        return reservation;
    }
}
