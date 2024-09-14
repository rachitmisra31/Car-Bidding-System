package com.bidding.PaymentService.feign;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserDto {

    private Long id;
    @Email(message = "Email must be valid")
    @NotBlank(message = "The field Email cannot be blank")
    private String email;
    @NotBlank(message = "The field Password cannot be blank")
    @Size(min = 8,message = "Password must be at least 8 characters in length")
    private String password;
    @NotBlank(message = "The field name should not be blank")
    private String name;
    private List<CarDto> cars;
}
