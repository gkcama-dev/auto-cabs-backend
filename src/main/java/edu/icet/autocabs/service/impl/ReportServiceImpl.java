package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.repository.PaymentRepository;
import edu.icet.autocabs.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PaymentRepository paymentRepository;

    @Override
    public Double getTotalRevenue() {
        return paymentRepository.getTotalRevenue();
    }

}
