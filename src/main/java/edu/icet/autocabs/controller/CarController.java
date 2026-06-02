package edu.icet.autocabs.controller;

import edu.icet.autocabs.dto.CarDto;
import edu.icet.autocabs.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody CarDto carDto) {
        carService.addCar(carDto);
        return new ResponseEntity<>("Car added successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateCarStatus(@PathVariable Long id, @RequestParam String status) {
        carService.updateCarStatus(id, status);
        return ResponseEntity.ok("Car status updated successfully!");
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<String> toggleCarActiveStatus(@PathVariable Long id, @RequestParam boolean active) {
        carService.toggleCarActiveStatus(id, active);
        String message = active ? "Car activated successfully!" : "Car deactivated successfully!";
        return ResponseEntity.ok(message);
    }

}
