package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}