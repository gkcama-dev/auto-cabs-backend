package edu.icet.autocabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private Long bookingId;
    private Double amount;
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paymentDate;
}
