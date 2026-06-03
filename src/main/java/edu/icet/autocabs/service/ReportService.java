package edu.icet.autocabs.service;

import java.util.List;
import java.util.Map;

public interface ReportService {

    Double getTotalRevenue();
    List<Map<String, Object>> getCarUtilizationReport();
    Long getBookingCountByPeriod(String startDate, String endDate);
    List<Map<String, Object>> getCustomerRentalReport();

}
