package com.martins.registrationService.service;

import com.martins.registrationService.dto.SellerDto;

import java.util.List;


public interface RegistrationService {


    String addSeller(SellerDto sellerDto);

    List<SellerDto> getSellersList();
}
