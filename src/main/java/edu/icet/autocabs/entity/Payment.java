package edu.icet.autocabs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("payment")
@Accessors(chain = true)
public class Payment {
    @Id
    private Long id;
    private Long bookingId;
    private Double amount;
    private String paymentMethod = "CARD";
    private String transactionId;
    private LocalDateTime paymentDate = LocalDateTime.now();
}
