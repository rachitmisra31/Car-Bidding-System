package com.carbidding.car_service.CarController;

import com.carbidding.car_service.CarEntity.Car;
import com.carbidding.car_service.CarService.CarService;
import com.carbidding.car_service.Dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars()
    {
        List<CarDto> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarsById(@PathVariable Long carId)
    {
        CarDto car = carService.getCarById(carId);
        if(car != null)
            return ResponseEntity.ok(car);
        else
            return ResponseEntity.notFound().build();
    }
    @PostMapping("/users/{userId}")
    public ResponseEntity<Void> createCar(@RequestBody CarDto carDto,@PathVariable Long userId)
    {
        carService.createCar(carDto,userId);
        return ResponseEntity.status(201).build();
    }
    @PutMapping("/{carId}/users/{userId}")
    public ResponseEntity<Void> updateCar(@PathVariable Long carId,@RequestBody CarDto carDto,@PathVariable Long userId)
    {
        carService.updateCar(carId,carDto,userId);
        return ResponseEntity.status(401).build();
    }
    @DeleteMapping("/carId")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId)
    {
        carService.deleteteCar(carId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarDto>> getCarsByBrand(@PathVariable String brand)
    {
        List<CarDto> cars = carService.getCarsByBrand(brand);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CarDto>> getCarsByCategory(@PathVariable String category)
    {
        List<CarDto> cars = carService.getCarsByCategory(category);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getAvailableCars()
    {
        List<CarDto> cars = carService.getAvailableCars();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<CarDto>> getCarsByUserId(@PathVariable Long userId){
        List<CarDto> cars = carService.getCarsByUserId(userId);
        return ResponseEntity.ok(cars);
    }
    @PutMapping("/{carId}/users/{userId}/buy")
    public ResponseEntity<String> buyCar(@PathVariable Long carId,@PathVariable Long userId)
    {
        carService.buyCar(carId,userId);
        return ResponseEntity.ok("car bought successfully");
    }
}
