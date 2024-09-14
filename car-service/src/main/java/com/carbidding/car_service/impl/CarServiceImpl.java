package com.carbidding.car_service.impl;

import com.carbidding.car_service.Dto.CarDto;

import java.util.List;

public interface CarServiceImpl {
    List<CarDto> getAllCars();
    CarDto getCarById(Long carId);
    List<CarDto>getCarsByBrand(String brand);
    List<CarDto>getCarsByCategory(String category);
    List<CarDto>getAvailableCars();
    void updateCar(Long carId, CarDto carDto,Long userId);
    void deleteteCar(Long carId);
    void createCar(CarDto carDto,Long userId);
}
