package com.biddingservice.bidding_service.Feign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
}
