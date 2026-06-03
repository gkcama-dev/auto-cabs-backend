package edu.icet.autocabs.controller;

import edu.icet.autocabs.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

    private final ReportService reportService;

    // Get total revenue earned from all payments (Admin Dashboard)
    @GetMapping("/revenue/total")
    public ResponseEntity<Double> getTotalRevenue() {
        return ResponseEntity.ok(reportService.getTotalRevenue());
    }

    // Get car utilization analytics report (Admin Dashboard)
    @GetMapping("/car-utilization")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getCarUtilizationReport() {
        return ResponseEntity.ok(reportService.getCarUtilizationReport());
    }

}
