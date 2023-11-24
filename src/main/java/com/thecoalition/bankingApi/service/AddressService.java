package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Address;
import com.thecoalition.bankingApi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    //add a new address
    public Address addAddress (Address newaddress){
        return addressRepository.save(newaddress);
    }
}
