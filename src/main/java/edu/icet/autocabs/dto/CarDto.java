package edu.icet.autocabs.dto;

import edu.icet.autocabs.entity.CarStatus;
import edu.icet.autocabs.entity.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String carNumber;
    private String brand;
    private String model;
    private CarType carType;
    private String fuelType;
    private Integer seatingCapacity;
    private Double dailyPrice;
    private CarStatus status;
}
