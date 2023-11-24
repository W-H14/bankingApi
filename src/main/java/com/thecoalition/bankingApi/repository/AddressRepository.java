package com.thecoalition.bankingApi.repository;

import com.thecoalition.bankingApi.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
