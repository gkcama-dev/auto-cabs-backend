package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.dto.BookingDto;
import edu.icet.autocabs.entity.Booking;
import edu.icet.autocabs.entity.BookingStatus;
import edu.icet.autocabs.entity.Car;
import edu.icet.autocabs.entity.CarStatus;
import edu.icet.autocabs.entity.PaymentStatus;
import edu.icet.autocabs.repository.BookingRepository;
import edu.icet.autocabs.repository.CarRepository;
import edu.icet.autocabs.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;

    @Override
    @Transactional // For concurrency handling and rollback protection
    public void createBooking(BookingDto bookingDto) {
        // Verify if car exists
        Car car = carRepository.findById(bookingDto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found!"));

        // Check car availability
        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Car is not available for rental at the moment!");
        }

        // Calculate total rental days
        long days = ChronoUnit.DAYS.between(bookingDto.getStartDate(), bookingDto.getEndDate());
        if (days <= 0) {
            throw new RuntimeException("End date must be after start date!");
        }

        // Calculate total price
        double totalPrice = car.getDailyPrice() * days;

        // Update car status to RENTED (Handles concurrent booking conflicts via Optimistic Locking)
        try {
            car.setStatus(CarStatus.RENTED);
            carRepository.save(car);
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException("Sorry, this car was just booked by another user! Please try again.");
        }

        // Save new booking record
        bookingRepository.save(new Booking()
                .setUserId(bookingDto.getUserId())
                .setCarId(bookingDto.getCarId())
                .setStartDate(bookingDto.getStartDate())
                .setEndDate(bookingDto.getEndDate())
                .setTotalDays((int) days)
                .setTotalPrice(totalPrice)
                .setBookingStatus(BookingStatus.PENDING)
                .setPaymentStatus(PaymentStatus.PENDING)
        );
    }

    @Override
    public List<BookingDto> getAllBookings() {
        Iterable<Booking> bookings = bookingRepository.findAll();
        List<BookingDto> dtoList = new ArrayList<>();
        for (Booking b : bookings) {
            dtoList.add(mapToDto(b));
        }
        return dtoList;
    }

    @Override
    public List<BookingDto> getBookingsByUserId(Long userId) {
        Iterable<Booking> bookings = bookingRepository.findByUserId(userId);
        List<BookingDto> dtoList = new ArrayList<>();
        for (Booking b : bookings) {
            dtoList.add(mapToDto(b));
        }
        return dtoList;
    }

    @Override
    @Transactional // For concurrency handling and rollback protection
    public void updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        BookingStatus newStatus = BookingStatus.valueOf(status.toUpperCase());
        booking.setBookingStatus(newStatus);

        // If booking is REJECTED, make the car AVAILABLE again
        if (newStatus == BookingStatus.REJECTED) {
            Car car = carRepository.findById(booking.getCarId()).orElse(null);
            if (car != null) {
                car.setStatus(CarStatus.AVAILABLE);
                carRepository.save(car);
            }
        }
        bookingRepository.save(booking);
    }

    @Override
    @Transactional // For concurrency handling and rollback protection
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        booking.setBookingStatus(BookingStatus.CANCELLED);

        // If booking is CANCELLED, make the car AVAILABLE again
        Car car = carRepository.findById(booking.getCarId()).orElse(null);
        if (car != null) {
            car.setStatus(CarStatus.AVAILABLE);
            carRepository.save(car);
        }
        bookingRepository.save(booking);
    }

    // Helper method for DTO mapping
    private BookingDto mapToDto(Booking b) {
        return new BookingDto(
                b.getId(), b.getUserId(), b.getCarId(),
                b.getStartDate(), b.getEndDate(), b.getTotalDays(), b.getTotalPrice(),
                b.getPaymentStatus(), b.getBookingStatus()
        );
    }
}
