package com.martins.orderManagementApp.service.resilience4j;

import com.martins.orderManagementApp.dto.SellerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserRegistrationResilience4j {
    Logger logger = LoggerFactory.getLogger(UserRegistrationResilience4j.class);
    private RestTemplate restTemplate;

    public UserRegistrationResilience4j(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @CircuitBreaker(name = "serviceRegisterSeller", fallbackMethod = "fallbackForRegisterSeller")
    @Retry(name = "retryServiceRegisterSeller", fallbackMethod = "retryfallback")
    public String  registerSeller(SellerDto sellerDto) {
        String response = restTemplate.postForObject("/addSeller", sellerDto, String.class);
        return response;
    }

    @CircuitBreaker(name = "serviceGetSellersList", fallbackMethod = "fallbackForGetSeller")
    public List<SellerDto> getSellersList() {
        logger.info("calling getSellerList()");
        return restTemplate.getForObject("/sellersList", List.class);
    }

    public String retryfallback(SellerDto sellerDto, Throwable t) {
        logger.error("Inside retryfallback, cause - {}", t.toString());
        return "Inside retryfallback method. Some error occurred while calling service for seller registration";
    }
    public String fallbackForRegisterSeller(SellerDto sellerDto, Throwable t) {
        logger.error("Inside circuit breaker fallbackForRegisterSeller, cause - {}", t.toString());
        return "Inside circuit breaker fallback method. Some error occurred while calling service for seller registration";
    }

    public List<SellerDto> fallbackForGetSeller(Throwable t) {
        logger.error("Inside fallbackForGetSeller, cause - {}", t.toString());
        SellerDto sd = new SellerDto();
        SellerDto sd2 = new SellerDto();

        //Add default seller 1
        sd.setFirstName("Jonh Circuit break");
        sd.setId(500);
        sd.setEmailId("default@default.com");

        //Add default seller 2
        sd2.setFirstName("Cardano");
        sd2.setId(100);
        sd2.setEmailId("cardano@cardano.com");

        List<SellerDto> defaultList = new ArrayList<>();
        defaultList.add(sd);
        defaultList.add(sd2);
        return defaultList;
    }

}
