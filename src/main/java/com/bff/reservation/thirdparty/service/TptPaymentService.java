package com.bff.reservation.thirdparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Payments;
import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.StatusPayment;
import com.bff.reservation.common.repository.PaymentRepository;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.thirdparty.dto.TptPaymentRequest;
import com.bff.reservation.thirdparty.dto.TptPaymentResponse;

@Service
public class TptPaymentService {

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private ReservationRepository reservationsRepository;

    public TptPaymentResponse createNewPayment(TptPaymentRequest paymentRequestDTO) {
        Payments payment = new Payments();
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPayment_date(paymentRequestDTO.getPaymentDate());
        payment.setPayment_method(paymentRequestDTO.getPaymentMethod());
        payment.setStatus(StatusPayment.unpaid);

        Reservations reservation = reservationsRepository.findById(paymentRequestDTO.getReservationId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        payment.setReservation(reservation);

        Payments savedPayment = paymentsRepository.save(payment);
        return convertToResponse(savedPayment);
    }

    private TptPaymentResponse convertToResponse(Payments payment) {
        TptPaymentResponse response = new TptPaymentResponse();
        response.setPaymentId(payment.getPayment_id());
        response.setReservationId(payment.getReservation().getReservation_id());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPayment_date());
        response.setPaymentMethod(payment.getPayment_method());
        response.setStatus(payment.getStatus().toString());
        return response;
    }
}

