package com.bidding.PaymentService.Controller;

import com.bidding.PaymentService.Dto.PaymentDto;
import com.bidding.PaymentService.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping
    public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentDto)
    {
        PaymentDto processPayment = paymentService.processPayment(paymentDto);
        return ResponseEntity.ok(processPayment);
    }
}
