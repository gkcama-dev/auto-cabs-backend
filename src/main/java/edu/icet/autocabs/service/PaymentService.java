package edu.icet.autocabs.service;

import edu.icet.autocabs.dto.PaymentDto;
import java.util.List;

public interface PaymentService {

    // Process payment and update booking status
    void processPayment(PaymentDto paymentDto);

    // Retrieve payment history by booking ID
    List<PaymentDto> getPaymentHistoryByBookingId(Long bookingId);

}
