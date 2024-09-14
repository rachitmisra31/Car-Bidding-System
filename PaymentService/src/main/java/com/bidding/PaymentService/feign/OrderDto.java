package com.bidding.PaymentService.feign;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private Long carId;
    private Double bidAmount;
    private String orderDate;
}
