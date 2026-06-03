package edu.icet.autocabs.repository;

import edu.icet.autocabs.entity.Payment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    // Check if a payment already exists for a specific booking
    Iterable<Payment> findByBookingId(Long bookingId);

    // Calculate total revenue from all payments (Returns 0 if no payments exist)
    @Query("SELECT COALESCE(SUM(amount), 0) FROM payment")
    Double getTotalRevenue();

}
