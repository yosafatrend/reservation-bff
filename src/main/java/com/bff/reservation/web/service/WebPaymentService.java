package com.bff.reservation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Payments;
import com.bff.reservation.common.repository.PaymentRepository;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.web.dto.WebPaymentRequest;
import com.bff.reservation.web.dto.WebPaymentResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class WebPaymentService {

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private ReservationRepository reservationsRepository;

    public List<WebPaymentResponse> getAllPayments() {
        return paymentsRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public WebPaymentResponse getPaymentById(Long id) {
        Optional<Payments> payment = paymentsRepository.findById(id);
        return payment.map(this::convertToResponse).orElse(null);
    }

    public WebPaymentResponse createPayment(WebPaymentRequest paymentRequest) {
        Payments payment = convertToEntity(paymentRequest);
        Payments createdPayment = paymentsRepository.save(payment);
        return convertToResponse(createdPayment);
    }

    public WebPaymentResponse updatePayment(Long id, WebPaymentRequest paymentRequest) {
        Optional<Payments> optionalPayment = paymentsRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payments existingPayment = optionalPayment.get();
            existingPayment.setReservation(reservationsRepository.findById(paymentRequest.getReservationId()).orElse(null));
            existingPayment.setAmount(paymentRequest.getAmount());
            existingPayment.setPayment_date(paymentRequest.getPaymentDate());
            existingPayment.setPayment_method(paymentRequest.getPaymentMethod());
            existingPayment.setStatus(paymentRequest.getStatus());
            Payments updatedPayment = paymentsRepository.save(existingPayment);
            return convertToResponse(updatedPayment);
        } else {
            return null;
        }
    }

    public boolean deletePayment(Long id) {
        if (paymentsRepository.existsById(id)) {
            paymentsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Payments convertToEntity(WebPaymentRequest paymentRequest) {
        Payments payment = new Payments();
        payment.setReservation(reservationsRepository.findById(paymentRequest.getReservationId()).orElse(null));
        payment.setAmount(paymentRequest.getAmount());
        payment.setPayment_date(paymentRequest.getPaymentDate());
        payment.setPayment_method(paymentRequest.getPaymentMethod());
        payment.setStatus(paymentRequest.getStatus());
        return payment;
    }

    private WebPaymentResponse convertToResponse(Payments payment) {
        WebPaymentResponse response = new WebPaymentResponse();
        response.setPaymentId(payment.getPayment_id());
        response.setReservationId(payment.getReservation().getReservation_id());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPayment_date());
        response.setPaymentMethod(payment.getPayment_method());
        response.setStatus(payment.getStatus());
        return response;
    }
}