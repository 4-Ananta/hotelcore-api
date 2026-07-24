package com.hotelcore.service;

import com.hotelcore.dto.PaymentRequest;
import com.hotelcore.dto.PaymentResponse;
import com.hotelcore.entity.Payment;
import com.hotelcore.entity.Reservation;
import com.hotelcore.entity.User;
import com.hotelcore.repository.PaymentRepository;
import com.hotelcore.repository.ReservationRepository;
import com.hotelcore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          ReservationRepository reservationRepository,
                          UserRepository userRepository){
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    private Reservation findReservasionById(Long reservationId){
        return reservationRepository.findById(reservationId)
                .orElseThrow(()-> new RuntimeException("Reservation tidak ditemukan"));
    }

    private User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User tidak ditemukan"));
    }

    public List<PaymentResponse> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentResponse> hasil = new ArrayList<>();
        for(Payment payment : payments) {
            PaymentResponse response = toResponse(payment);
            hasil.add(response);
        }
        return hasil;
    }

    public PaymentResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Payment tidak ditemukan"));
        return toResponse(payment);
    }

    public PaymentResponse savePayment(PaymentRequest request) {
        Payment payment = toEntity(request);
        Payment saved = paymentRepository.save(payment);
        return toResponse(saved);
    }

    public PaymentResponse update(Long id, PaymentRequest request) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Payment tidak ditemukan"));

        Reservation reservation = findReservasionById(request.getReservation());
        User user = findByUsername(request.getCreatedBy());

        existing.setReservation(reservation);
        existing.setAmount(request.getAmount());
        existing.setPaymentMethod(request.getPaymentMethod());
        existing.setPaymentStatus(request.getPaymentStatus());
        existing.setReferenceNumber(request.getReferenceNumber());

        Payment saved = paymentRepository.save(existing);
        return toResponse(saved);
    }

    public void delete(Long id) {paymentRepository.deleteById(id);}

    private PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();

        response.setId(payment.getId());
        response.setReservation(payment.getReservation().getId());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setReferenceNumber(payment.getReferenceNumber());
        response.setCreatedBy(payment.getCreatedBy().getFullName());
        response.setCreatedAt(payment.getCreatedAt());

        return response;
    }

    private Payment toEntity(PaymentRequest request) {
        Reservation reservation = findReservasionById(request.getReservation());
        User user = findByUsername(request.getCreatedBy());

        Payment payment = new Payment();

        payment.setReservation(reservation);
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setReferenceNumber(request.getReferenceNumber());
        payment.setCreatedBy(user);

        return payment;
    }
}
