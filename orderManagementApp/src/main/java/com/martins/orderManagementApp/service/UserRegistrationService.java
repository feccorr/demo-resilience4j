package com.martins.orderManagementApp.service;

import com.martins.orderManagementApp.dto.SellerDto;

import java.util.List;


public interface UserRegistrationService {
    String registerSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
