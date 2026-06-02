package edu.icet.autocabs.controller;

import edu.icet.autocabs.dto.BookingDto;
import edu.icet.autocabs.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    // Create a new booking (Customer)
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto) {
        try {
            bookingService.createBooking(bookingDto);
            return new ResponseEntity<>("Booking placed successfully!", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get all bookings (Admin Panel)
    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Get booking history for a specific user (Customer Profile)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    // Update booking status (Admin Approve/Reject)
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateBookingStatus(@PathVariable Long id, @RequestParam String status) {
        bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok("Booking status updated successfully!");
    }

    // Cancel a booking (Customer/Admin)
    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully!");
    }

}
