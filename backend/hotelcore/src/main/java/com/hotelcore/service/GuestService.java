package com.hotelcore.service;

import com.hotelcore.dto.GuestRequest;
import com.hotelcore.dto.GuestResponse;
import com.hotelcore.entity.Guest;
import com.hotelcore.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestResponse> findAll() {
        List<Guest> guests = guestRepository.findAll();
        List<GuestResponse> hasil = new ArrayList<>();
        for (Guest guest : guests) {
            GuestResponse response = toResponse(guest);
            hasil.add(response);
        }
        return hasil;
    }

    public GuestResponse findById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest tidak ditemukan"));
        return toResponse(guest);
    }

    public GuestResponse saveGuest(GuestRequest request){
        Guest guest = toEntity(request);
        Guest saved = guestRepository.save(guest);
        return toResponse(saved);
    }

    public GuestResponse update(Long id, GuestRequest request) {
        Guest existing = guestRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Guest tidak ditemukan"));

        existing.setIdentityNumber(request.getIdentityNumber());
        existing.setIdentityType(request.getIdentityType());
        existing.setFullName(request.getFullName());
        existing.setGender(request.getGender());
        existing.setPhone(request.getPhone());
        existing.setEmail(request.getEmail());
        existing.setAddress(request.getAddress());
        existing.setNationality(request.getNationality());
        existing.setIsBlacklisted(request.getIsBlacklisted());

        Guest saved = guestRepository.save(existing);

        return toResponse(saved);
    }

    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    private GuestResponse toResponse(Guest guest) {
        GuestResponse response = new GuestResponse();

        response.setId(guest.getId());
        response.setFullName(guest.getFullName());
        response.setIdentityType(guest.getIdentityType());
        response.setIdentityNumber(guest.getIdentityNumber());
        response.setGender(guest.getGender());
        response.setAddress(guest.getAddress());
        response.setEmail(guest.getEmail());
        response.setPhone(guest.getPhone());
        response.setNationality(guest.getNationality());
        response.setIsBlacklisted(guest.getIsBlacklisted());
        response.setCreatedAt(guest.getCreatedAt());
        response.setUpdatedAt(guest.getUpdatedAt());

        return response;
    }

    private Guest toEntity(GuestRequest request) {
        Guest guest = new Guest();

        guest.setFullName(request.getFullName());
        guest.setIdentityType(request.getIdentityType());
        guest.setIdentityNumber(request.getIdentityNumber());
        guest.setGender(request.getGender());
        guest.setAddress(request.getAddress());
        guest.setNationality(request.getNationality());
        guest.setEmail(request.getEmail());
        guest.setPhone(request.getPhone());
        guest.setIsBlacklisted(request.getIsBlacklisted());

        return guest;
    }

}
