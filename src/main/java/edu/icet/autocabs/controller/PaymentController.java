package edu.icet.autocabs.controller;

import edu.icet.autocabs.dto.PaymentDto;
import edu.icet.autocabs.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    // Process a new payment
    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentDto paymentDto) {

        try {
            paymentService.processPayment(paymentDto);
            return new ResponseEntity<>("Payment processed successfully! Booking approved.", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // Get payment history by booking ID (Admin/Customer Profile)
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<PaymentDto>> getPaymentHistoryByBookingId(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentHistoryByBookingId(bookingId));
    }

}
