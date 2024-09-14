package com.bidding.OrderService.Dto;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private Long userId;
    private Long carId;
    private Double bidAmount;
    private LocalDateTime orderDate;
}
