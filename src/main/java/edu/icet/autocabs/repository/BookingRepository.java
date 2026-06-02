package edu.icet.autocabs.repository;

import edu.icet.autocabs.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    // Fetch booking history for a specific user
    Iterable<Booking> findByUserId(Long userId);
}
