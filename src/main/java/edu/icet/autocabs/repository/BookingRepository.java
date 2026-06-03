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

    // Fetch customer rental analytics (Booking counts and total spending grouped by customer)
    @Query("SELECT u.id as customer_id, u.name as customer_name, u.email as customer_email, " +
            "COUNT(b.id) as total_bookings, COALESCE(SUM(b.total_price), 0) as total_spent " +
            "FROM users u " +
            "LEFT JOIN booking b ON u.id = b.customer_id " +
            "WHERE u.role = 'CUSTOMER' " +
            "GROUP BY u.id, u.name, u.email " +
            "ORDER BY total_bookings DESC")

    List<Map<String, Object>> getCustomerRentalAnalytics();

}
