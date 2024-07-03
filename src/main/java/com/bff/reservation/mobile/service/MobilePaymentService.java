package com.bff.reservation.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Payments;
import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.StatusPayment;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.PaymentRepository;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.mobile.dto.MobilePaymentDetailResponse;
import com.bff.reservation.mobile.dto.MobilePaymentRequest;
import com.bff.reservation.mobile.dto.MobilePaymentResponse;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobilePaymentService {

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private ReservationRepository reservationsRepository;

    @Autowired
    private UserRepository usersRepository;

    public List<MobilePaymentResponse> getUserPayments(String username) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Payments> payments = paymentsRepository.findByReservation_Users(user);
        return payments.stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }

    public MobilePaymentDetailResponse createPayment(String username, MobilePaymentRequest paymentRequest) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        Reservations reservation = reservationsRepository.findById(paymentRequest.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Payments payment = new Payments();
        payment.setReservation(reservation);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPayment_date(new Date());
        payment.setPayment_method(paymentRequest.getPaymentMethod());
        payment.setStatus(StatusPayment.paid);

        Payments savedPayment = paymentsRepository.save(payment);
        return convertToDetailResponse(savedPayment);
    }

    public MobilePaymentDetailResponse updatePayment(String username, Long id, MobilePaymentRequest paymentRequest) {
        Payments payment = paymentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        Reservations reservation = reservationsRepository.findById(paymentRequest.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        payment.setReservation(reservation);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPayment_method(paymentRequest.getPaymentMethod());
        payment.setStatus(StatusPayment.paid);

        Payments updatedPayment = paymentsRepository.save(payment);
        return convertToDetailResponse(updatedPayment);
    }

    private MobilePaymentResponse convertToSummaryResponse(Payments payment) {
        MobilePaymentResponse response = new MobilePaymentResponse();
        response.setPaymentId(payment.getPayment_id());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPayment_date());
        response.setPaymentMethod(payment.getPayment_method());
        response.setStatus(payment.getStatus().toString());
        return response;
    }

    private MobilePaymentDetailResponse convertToDetailResponse(Payments payment) {
        MobilePaymentDetailResponse response = new MobilePaymentDetailResponse();
        response.setPaymentId(payment.getPayment_id());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPayment_date());
        response.setPaymentMethod(payment.getPayment_method());
        response.setStatus(payment.getStatus().toString());
        response.setReservationId(payment.getReservation().getReservation_id());
        return response;
    }
}
