package com.bidding.PaymentService;

import com.bidding.PaymentService.Dto.PaymentDto;
import com.bidding.PaymentService.Entity.Payment;
import com.bidding.PaymentService.Repository.PaymentRepository;
import com.bidding.PaymentService.Service.PaymentService;
import com.bidding.PaymentService.feign.OrderDto;
import com.bidding.PaymentService.feign.OrderFeignClient;
import com.bidding.PaymentService.feign.UserDto;
import com.bidding.PaymentService.feign.UserFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Mock
    private UserFeignClient userFeignClient;
    @Mock
    OrderFeignClient orderFeignClient;
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;
     @Test
    public void testProcessPayment()
     {


         UserDto userDto = new UserDto();
         userDto.setId(1L);
         userDto.setEmail("test@example.com");
         userDto.setName("Steve Davis");

         OrderDto orderDto = new OrderDto();
         orderDto.setId(1L);
         orderDto.setUserId(1L);
         orderDto.setCarId(2L);
         orderDto.setBidAmount(15000.0);

         Payment savedPayment = new Payment();
         savedPayment.setId(1L);
         savedPayment.setUserId(1L);
         savedPayment.setOrderId(1L);
         savedPayment.setAmount(15000.0);
         savedPayment.setPaymentDate(LocalDateTime.now());
         savedPayment.setStatus("Processed");

         PaymentDto paymentDto = new PaymentDto();
         paymentDto.setUserId(1L);
         paymentDto.setOrderId(1L);
         paymentDto.setAmount(15000.0);


         doReturn(userDto).when(userFeignClient.getUserById(1L));
         doReturn(orderDto).when(orderFeignClient).getOrderById(1L);
         doReturn(savedPayment).when(paymentRepository).save(any(Payment.class));
         PaymentDto processedPayment = paymentService.processPayment(paymentDto);
         assertNotNull(processedPayment);
         assertEquals(savedPayment.getId(),processedPayment.getId());
         assertEquals(savedPayment.getStatus(),processedPayment.getStatus());
         verify(paymentRepository,times(1)).save(any(Payment.class));

     }
}
