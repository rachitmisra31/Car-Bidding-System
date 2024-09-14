package com.bidding.PaymentService.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class PaymentDto {
    private Long id;
    private Long userId;
    private Long orderId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String status;
}
