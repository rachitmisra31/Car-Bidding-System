package com.biddingservice.bidding_service.Repository;

import com.biddingservice.bidding_service.Entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findByCarId(Long carId);
    List<Bid> findByUserId(Long userId);
}
