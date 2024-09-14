package com.carbidding.user_service.UserRepository;

import com.carbidding.user_service.UserEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String Email);



}
