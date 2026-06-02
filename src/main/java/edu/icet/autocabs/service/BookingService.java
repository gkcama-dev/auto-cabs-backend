package edu.icet.autocabs.service;

import edu.icet.autocabs.dto.BookingDto;
import java.util.List;

public interface BookingService {

    void createBooking(BookingDto bookingDto);
    List<BookingDto> getAllBookings();
    List<BookingDto> getBookingsByUserId(Long userId);
    void updateBookingStatus(Long id, String status);
    void cancelBooking(Long id);

}
