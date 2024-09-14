package com.carbidding.user_service.UserEntity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String jwt;
    private String userName;

    public AuthResponse(String jwt) {
    }
}
