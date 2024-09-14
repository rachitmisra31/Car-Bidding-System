package com.carbidding.car_service.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String category;
    private String model;
    private boolean available;
    private Long userId;
}
