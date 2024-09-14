package com.bidding.PaymentService.Service;

import com.bidding.PaymentService.Dto.PaymentDto;
import com.bidding.PaymentService.Entity.Payment;
import com.bidding.PaymentService.Repository.PaymentRepository;
import com.bidding.PaymentService.feign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private OrderFeignClient orderFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    public PaymentDto processPayment(PaymentDto paymentDto){
        UserDto user = userFeignClient.getUserById(paymentDto.getUserId());
        OrderDto order = orderFeignClient.getOrderById(paymentDto.getOrderId());
        CarDto car = carFeignClient.getCarById(order.getCarId());
        Payment payment = new Payment();
        payment.setUserId(paymentDto.getUserId());
        payment.setOrderId(paymentDto.getOrderId());
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setStatus("SUCCESS");
        payment = paymentRepository.save(payment);
        paymentDto.setId(payment.getId());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setStatus(payment.getStatus());
        return paymentDto;
    }

}
