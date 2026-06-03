package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.repository.BookingRepository;
import edu.icet.autocabs.repository.PaymentRepository;
import edu.icet.autocabs.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public Double getTotalRevenue() {
        return paymentRepository.getTotalRevenue();
    }

    @Override
    public List<Map<String, Object>> getCarUtilizationReport() {
        return bookingRepository.getCarUtilizationAnalytics();
    }

    @Override
    public Long getBookingCountByPeriod(String startDate, String endDate) {
        // Fetch booking count for the specified date range from repository
        return bookingRepository.getBookingCountByPeriod(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getCustomerRentalReport() {
        return bookingRepository.getCustomerRentalAnalytics();
    }

}
