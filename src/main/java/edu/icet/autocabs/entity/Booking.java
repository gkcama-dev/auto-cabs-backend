package edu.icet.autocabs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("booking")
@Accessors(chain = true)
public class Booking {
    @Id
    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;
    private Double totalPrice;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private BookingStatus bookingStatus = BookingStatus.PENDING;
}
