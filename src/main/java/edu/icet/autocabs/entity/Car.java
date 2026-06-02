package edu.icet.autocabs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("car")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Car {

    @Id
    private Long id;
    private String carNumber;
    private String brand;
    private String model;
    private CarType carType;
    private String fuelType;
    private Integer seatingCapacity;
    private Double dailyPrice;
    private CarStatus status = CarStatus.AVAILABLE;

}
