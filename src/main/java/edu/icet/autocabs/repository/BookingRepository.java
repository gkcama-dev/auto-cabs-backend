package edu.icet.autocabs.repository;

import edu.icet.autocabs.entity.Booking;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    // Fetch booking history for a specific user
    Iterable<Booking> findByUserId(Long userId);

    // Fetch car utilization analytics (Booking counts grouped by car ID in descending order)
    @Query("SELECT car_id, COUNT(id) as booking_count FROM booking GROUP BY car_id ORDER BY booking_count DESC")
    List<Map<String, Object>> getCarUtilizationAnalytics();

    // Get total booking count within a specific date range
    @Query("SELECT COUNT(id) FROM booking WHERE booking_date BETWEEN :startDate AND :endDate")
    Long getBookingCountByPeriod(String startDate, String endDate);
    
}
