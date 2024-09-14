package com.carbidding.user_service.UserController;

import com.carbidding.user_service.Dto.UserDto;
import com.carbidding.user_service.Feign.Car;

import com.carbidding.user_service.Feign.OrderDto;
import com.carbidding.user_service.UserService.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")

    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto)
    {

        return userService.register(userDto);
    }
    @PostMapping("/login")

    public ResponseEntity<String> login( @RequestBody UserDto userDto)
    {
      return userService.login(userDto);
    }

    @PutMapping("/{userId}/cars/{carId}/buy")
    public ResponseEntity<String> buyCar(@PathVariable Long userId,@PathVariable Long carId)
    {
        try{
            userService.buyCar(carId,userId);
            return ResponseEntity.ok("Car bought successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buying car");
        }
    }
    @PutMapping("/{userId}/cars/{carId}/sell")
    public ResponseEntity<String> sellCar(@PathVariable Long userId,@PathVariable Long carId)
    {
        try {
            userService.sellCar(carId, userId);
            return ResponseEntity.ok("Car sold successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Selling car");
        }
    }
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId)
    {
       return userService.getUserById(userId);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@Valid @PathVariable Long userId,@RequestBody UserDto userDto)
    {
        return userService.updateUser(userId,userDto);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
        return userService.deleteUser(userId);
    }
    @PostMapping("/{userId}/orders")
    public OrderDto placeOrder(@PathVariable Long userId,@RequestBody OrderDto orderDto)
    {
        orderDto.setUserId(userId);
        return userService.placeOrder(orderDto);
    }
    @GetMapping("/{userId}/orders")
    public List<OrderDto> getOrdersById(@PathVariable Long userId){
        return userService.getOrdersByUserId(userId);
    }
}
