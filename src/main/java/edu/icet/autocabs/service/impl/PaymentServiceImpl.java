package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.dto.PaymentDto;
import edu.icet.autocabs.entity.Booking;
import edu.icet.autocabs.entity.BookingStatus;
import edu.icet.autocabs.entity.Payment;
import edu.icet.autocabs.entity.PaymentStatus;
import edu.icet.autocabs.repository.BookingRepository;
import edu.icet.autocabs.repository.PaymentRepository;
import edu.icet.autocabs.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional // Ensures atomicity for payment processing and booking updates
    public void processPayment(PaymentDto paymentDto) {

        // Verify if the booking exists
        Booking booking = bookingRepository.findById(paymentDto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found for this payment!"));

        // Save payment details
        paymentRepository.save(new Payment()
                .setBookingId(paymentDto.getBookingId())
                .setAmount(paymentDto.getAmount())
                .setPaymentMethod(paymentDto.getPaymentMethod() != null ? paymentDto.getPaymentMethod() : "CARD")
                .setTransactionId(paymentDto.getTransactionId())
        );

        // Update payment and booking status values
        booking.setPaymentStatus(PaymentStatus.PAID);
        booking.setBookingStatus(BookingStatus.APPROVED);

        // Persist updated booking details
        bookingRepository.save(booking);

    }

    @Override
    public List<PaymentDto> getPaymentHistoryByBookingId(Long bookingId) {

        Iterable<Payment> payments = paymentRepository.findByBookingId(bookingId);
        List<PaymentDto> dtoList = new ArrayList<>();

        for (Payment p : payments) {
            dtoList.add(new PaymentDto(
                    p.getId(),
                    p.getBookingId(),
                    p.getAmount(),
                    p.getPaymentMethod(),
                    p.getTransactionId(),
                    p.getPaymentDate()
            ));
        }
        return dtoList;

    }
}
