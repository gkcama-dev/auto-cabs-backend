package edu.icet.autocabs.service;

import edu.icet.autocabs.dto.CarDto;

import java.util.List;

public interface CarService {

    void addCar(CarDto carDto);
    List<CarDto> getAllCars();
    void updateCarStatus(Long id, String status);
    void toggleCarActiveStatus(Long id, boolean active);

}
