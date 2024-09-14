package com.bidding.OrderService.Repository;

import com.bidding.OrderService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {


}
