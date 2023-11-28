//package com.thecoalition.bankingApi.service;
//
//import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
//import com.thecoalition.bankingApi.model.Address;
//import com.thecoalition.bankingApi.repository.AddressRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AddressService {
//
//    @Autowired
//    AddressRepository addressRepository;
//    private final Logger logger = LoggerFactory.getLogger(AddressService.class);
//
//    //add a new address
//    public Address addAddress (Address newAddress) throws ResourceNotFoundException {
//        try {
//            logger.info("New address created");
//            return addressRepository.save(newAddress);
//        } catch (Exception e) {
//            logger.error("Unable to create address", e);
//            throw new ResourceNotFoundException("Unable to create address");
//
//        }
//    }
//}
//
//
//
//
//
////    @Autowired
////    AddressRepository addressRepository;
////    private final Logger logger = LoggerFactory.getLogger(AddressService.class);
////
////    //add a new address
////    public Address addAddress (Address newaddress){
////        logger.info("new address created");
////        return addressRepository.save(newaddress);
////    }
////}
