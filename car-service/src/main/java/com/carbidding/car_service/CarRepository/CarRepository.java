package com.carbidding.car_service.CarRepository;

import com.carbidding.car_service.CarEntity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
  List<Car> findByAvailable(boolean available);
  List<Car> findByUserId(Long userId);

  List<Car>findByBrand(String brand);

  List<Car> findByCategory(String category);

}
