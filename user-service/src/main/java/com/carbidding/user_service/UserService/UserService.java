package com.carbidding.user_service.UserService;

import com.carbidding.user_service.Dto.UserDto;
import com.carbidding.user_service.Feign.*;
import com.carbidding.user_service.UserEntity.User;
import com.carbidding.user_service.UserRepository.UserRepository;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeignInterface feignInterface;
    @Autowired
    private BidFeignClient bidFeignClient;
    @Autowired
    private OrderFeignClient orderFeignClient;
    public ResponseEntity<String> register(UserDto userDto)
    {
        logger.info("Attempting to register user: {}",userDto.getEmail());
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent())
        {
            throw new RuntimeException("Email already in use"+userDto.getEmail());
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());

            userRepository.save(user);
            return ResponseEntity.ok("User Registered Successfully");


    }
    public ResponseEntity<String> login(UserDto userDto)
    {
        logger.info("Attempting to login user: {}",userDto.getEmail());
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getPassword().equals(userDto.getPassword())) {
                return ResponseEntity.ok("Login Success");
            }
        }
        return ResponseEntity.status(401).body("Invalid Email or Password");
    }
   /* public Optional<User> login(String email, String password)
    {
        return userRepository.findByEmail(email).filter(user -> user.getPassword().equals(password));
    }*/
    public UserDto getUserById(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow();
        return convertToDto(user);
    }
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> users = userRepository.findAll().stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setName(user.getName());
            return userDto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    public void buyCar(Long userId,Long carId)
    {
        logger.info("Attempting to buying a car:{}",userId);
         feignInterface.buyCar(carId,userId);
        logger.info("Car bought is:{}",carId);
    }
    public void sellCar(Long userId,Long carId)
    {
             feignInterface.sellCar(carId,userId);
    }

    public ResponseEntity<String> updateUser(Long userId,UserDto userDto)
    {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            if(userDto.getPassword() != null && !userDto.getPassword().isEmpty() )
                user.setPassword(userDto.getPassword());

             userRepository.save(user);
             return ResponseEntity.ok("User Updated Successfully");
        }
        else
            return ResponseEntity.notFound().build();
    }
    public ResponseEntity<String >deleteUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent())
        {
            userRepository.deleteById(userId);
            return ResponseEntity.ok("User deleted!");
        }
        else
            return ResponseEntity.notFound().build();
    }
    private UserDto convertToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setCars(feignInterface.getCarsByUserId(user.getId()));
        return userDto;
    }

   public OrderDto placeOrder(OrderDto orderDto)
   {
       return orderFeignClient.createOrder(orderDto);
   }
   public List<OrderDto> getOrdersByUserId(Long userId)
   {
       return orderFeignClient.getOrdersByUserId(userId);
   }


}
