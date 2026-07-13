package com.hotelcore.service;

import com.hotelcore.entity.Guest;
import com.hotelcore.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest tidak ditemukan"));
    }

    public Guest saveGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public Guest update(Long id, Guest guest) {

        Guest existing = findById(id);

        existing.setIdentityNumber(guest.getIdentityNumber());
        existing.setIdentityType(guest.getIdentityType());
        existing.setFullName(guest.getFullName());
        existing.setGender(guest.getGender());
        existing.setPhone(guest.getPhone());
        existing.setEmail(guest.getEmail());
        existing.setAddress(guest.getAddress());
        existing.setNationality(guest.getNationality());
        existing.setIsBlacklisted(guest.getIsBlacklisted());

        return guestRepository.save(existing);
    }

    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

}
