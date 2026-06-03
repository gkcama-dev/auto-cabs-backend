package edu.icet.autocabs.repository;

import edu.icet.autocabs.entity.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    // Check if a payment already exists for a specific booking
    Iterable<Payment> findByBookingId(Long bookingId);

}
