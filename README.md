# Car-Bidding-System
This Repository will provide the backend functionality of the Car Bidding System using Microservices Architecture.
The User will register on the portal and then wil try to login and once he is logged in the jwt token will be generated for him.
Only the user with the JWT token will be able to login.
Then the user will select a car of his choice and place a bid for that car.
Multiple users can place a bid on one car but the second bidder will have to place a bid which is always greater then the first one and so on and then at last the last bidder will buy the car.
After the bidder wins the bid then he will place an order for that car which will be handled in the Order Microservice.
At last the bidder will make the payment for that particular Order that was placed which is handled in the Payment Microservice.
