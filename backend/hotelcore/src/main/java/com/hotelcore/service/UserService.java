package com.hotelcore.service;

import com.hotelcore.dto.UserRequest;
import com.hotelcore.dto.UserResponse;
import com.hotelcore.entity.User;
import com.hotelcore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> hasil = new ArrayList<>();
        for (User user : users) {
            UserResponse response = toResponse(user);
            hasil.add(response);
        }
        return hasil;
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        return toResponse(user);
    }

    public UserResponse saveUser(UserRequest request) {
        User user = toEntity(request);
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public UserResponse update(Long id, UserRequest request) {
        User existing = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User tidak ditemukan"));

        existing.setUsername(request.getUsername());
        existing.setPassword(passwordEncoder.encode(request.getPassword()));
        existing.setFullName(request.getFullName());
        existing.setRole(request.getRole());

        User saved = userRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        response.setIsActive(user.getIsActive());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    private User toEntity(UserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());

        return user;
    }


}
