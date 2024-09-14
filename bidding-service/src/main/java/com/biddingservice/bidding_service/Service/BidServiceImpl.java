package com.biddingservice.bidding_service.Service;

import com.biddingservice.bidding_service.Dto.BidDto;
import com.biddingservice.bidding_service.Entity.Bid;
import com.biddingservice.bidding_service.Feign.CarDto;
import com.biddingservice.bidding_service.Feign.CarFeignClient;
import com.biddingservice.bidding_service.Feign.UserDto;
import com.biddingservice.bidding_service.Feign.UserFeignClient;
import com.biddingservice.bidding_service.Repository.BidRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService{
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private CarFeignClient carFeignClient;
    @Override
    public List<BidDto> getBidsByUserId(Long userId) {
        return bidRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BidDto convertToDto(Bid bid) {
        BidDto bidDto = new BidDto();
        bidDto.setId(bid.getId());
        bidDto.setUserId(bid.getUserId());
        bidDto.setCarId(bid.getCarId());
        bidDto.setAmount(bid.getAmount());
        bidDto.setWinningBid(bid.isWinningBid());
        return bidDto;
    }


    @Override
    public List<BidDto> getBidsByCarId(Long carId) {
        return bidRepository.findByCarId(carId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BidDto placeBid(BidDto bidDto) {
        UserDto userDto = userFeignClient.getUserById(bidDto.getUserId());
        CarDto carDto = carFeignClient.getCarsById(bidDto.getCarId());
        if (userDto != null && carDto != null) {
            Bid bid = new Bid();
            bid.setUserId(bidDto.getUserId());
            bid.setCarId(bidDto.getCarId());
            bid.setAmount(bidDto.getAmount());
            bid.setWinningBid(false);
            Bid saveBid = bidRepository.save(bid);
            bidDto.setId(saveBid.getId());
            return bidDto;
        } else {
            throw new RuntimeException("User Not Found");
        }
    }
    @Override
    public Long getUserWithHighestBid(Long carId) {
        List<Bid> bids = bidRepository.findByCarId(carId);
        return bids.stream().max(Comparator.comparingDouble(Bid::getAmount))
                .map(Bid::getUserId).orElse(null);
    }

}
