package edu.icet.autocabs.service.impl;

import edu.icet.autocabs.dto.CarDto;
import edu.icet.autocabs.entity.Car;
import edu.icet.autocabs.entity.CarStatus;
import edu.icet.autocabs.repository.CarRepository;
import edu.icet.autocabs.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void addCar(CarDto carDto) {

        carRepository.save(new Car()
                .setCarNumber(carDto.getCarNumber())
                .setBrand(carDto.getBrand())
                .setModel(carDto.getModel())
                .setCarType(carDto.getCarType())
                .setFuelType(carDto.getFuelType())
                .setSeatingCapacity(carDto.getSeatingCapacity())
                .setDailyPrice(carDto.getDailyPrice())
                .setStatus(CarStatus.AVAILABLE)
        );
    }

    @Override
    public List<CarDto> getAllCars() {

        Iterable<Car> cars = carRepository.findAll();
        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : cars) {
            CarDto carDto = new CarDto(
                    car.getId(),
                    car.getCarNumber(),
                    car.getBrand(),
                    car.getModel(),
                    car.getCarType(),
                    car.getFuelType(),
                    car.getSeatingCapacity(),
                    car.getDailyPrice(),
                    car.getStatus()
            );
            carDtoList.add(carDto);
        }

        return carDtoList;
    }

    @Override
    public void updateCarStatus(Long id, String status) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        car.setStatus(CarStatus.valueOf(status.toUpperCase()));
        carRepository.save(car);
    }

    @Override
    public void toggleCarActiveStatus(Long id, boolean active) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        // active true -> AVAILABLE, false -> DEACTIVATED
        if (active) {
            car.setStatus(CarStatus.AVAILABLE);
        } else {
            car.setStatus(CarStatus.DEACTIVATED);
        }

        carRepository.save(car);
    }


}
