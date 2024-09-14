package com.carbidding.car_service.CarService;

import com.carbidding.car_service.CarEntity.Car;
import com.carbidding.car_service.CarRepository.CarRepository;
import com.carbidding.car_service.Dto.CarDto;
import com.carbidding.car_service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService implements CarServiceImpl {
    @Autowired
    private CarRepository carRepository;


    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public List<CarDto> getCarsByUserId(Long userId)
    {
        List<Car> cars = carRepository.findByUserId(userId);
        return cars.stream().map(car->{
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setBrand(car.getBrand());
            carDto.setModel(car.getModel());
            carDto.setCategory(car.getCategory());
            carDto.setAvailable(car.isAvailable());
            carDto.setUserId(car.getUserId());
            return carDto;
        }).collect(Collectors.toList());

    }

    @Override
    public CarDto getCarById(Long carId) {
        return carRepository.findById(carId).map(this::convertToDto).orElse(null);
    }

    @Override
    public List<CarDto> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getCarsByCategory(String category) {
        return carRepository.findByCategory(category).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> getAvailableCars() {
        return carRepository.findByAvailable(true).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void updateCar(Long carId, CarDto carDto,Long userId) {
        Car car = carRepository.findById(carId).orElse(null);
        if(car != null)
        {
            car.setBrand(carDto.getBrand());
            car.setModel(carDto.getModel());
            car.setCategory(carDto.getCategory());
            car.setAvailable(carDto.isAvailable());
            car.setUserId(userId);
            carRepository.save(car);
        }
    }

    @Override
    public void deleteteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public void createCar(CarDto carDto,Long userId) {
        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setCategory(carDto.getCategory());
        car.setAvailable(carDto.isAvailable());
        car.setUserId(carDto.getUserId());
        carRepository.save(car);
    }
    private CarDto convertToDto(Car car)
    {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setCategory(car.getCategory());
        carDto.setModel(car.getModel());
        carDto.setAvailable(car.isAvailable());
        carDto.setUserId(car.getUserId());
        return carDto;
    }
    public void buyCar(Long carId, Long userId)
    {
        CarDto carDto = getCarById(carId);
        if(carDto != null)
        {
            carDto.setAvailable(false);
            updateCar(carId,carDto,userId);
        }
        else
            throw new IllegalArgumentException("Car not found with id:"+carId);
    }
    public void sellCar(Long carId, Long userId, CarDto carDto)
    {
        CarDto existingCar = getCarById(carId);
        if(existingCar != null)
        {
            if(carDto != null)
            {
                existingCar.setBrand(carDto.getBrand());
                existingCar.setModel(carDto.getModel());
                existingCar.setCategory(carDto.getCategory());
                existingCar.setAvailable(carDto.isAvailable());

            }
            updateCar(carId,existingCar,userId);
        }
        else
        {
            throw new IllegalArgumentException("Car not found with id: "+carId);
        }
    }
}
