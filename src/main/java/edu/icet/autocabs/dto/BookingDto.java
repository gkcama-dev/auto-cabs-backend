package edu.icet.autocabs.dto;

import edu.icet.autocabs.entity.BookingStatus;
import edu.icet.autocabs.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;
    private Double totalPrice;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
}
