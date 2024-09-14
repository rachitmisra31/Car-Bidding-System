package com.carbidding.user_service.UserEntity;

import com.carbidding.user_service.Feign.Car;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private Set<String> roles = new HashSet<>();

}

