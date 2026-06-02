package edu.icet.autocabs.repository;

import edu.icet.autocabs.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Iterable<Car> findByBrand(String brand);

}
