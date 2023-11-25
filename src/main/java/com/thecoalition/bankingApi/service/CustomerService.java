package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.ResourceNotFoundException;
import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    Account account;
    @Autowired
    AddressService addressService;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public void confirmCustomer(Long customerId) throws ResourceNotFoundException{
        if(!customerRepository.existsById(customerId)){
            logger.error("Customer Not confirmed");
            throw new ResourceNotFoundException("Customer with id '" + customerId + "' is not found");
        }
    }

    //add customer
    public Customer createCustomer(Customer customer){
    logger.info("successfully created Customer");
    return customerRepository.save(customer);
    }
    //get all customers
    public Iterable<Customer> getAllCustomers(){
    logger.info("successfully retrieved all customers");
        return customerRepository.findAll();
    }

    //get customers by AccountID Iterable<>
    public Iterable<Customer> getCustomerByAccountId(Long accountId){
        Customer findCustomerByAccount = new Customer();
        logger.info("Successfully retrieved customer by account Id");
        return customerRepository.findCustomersByAccountId(accountId);
    }


    //get customers by ID Optional<>
    public Optional<Customer> getCustomerById(Long customerId){
        logger.info("Successfully retrieved customer by customer ID");
        return customerRepository.findById(customerId);
    }

    //edit customers use Optional<> and a loop to edit specifics
    public Customer editCustomer(Customer customer, Long customerId) throws ResourceNotFoundException {
        Optional<Customer> findCustomerById = customerRepository.findById(customerId);

        if(findCustomerById.isPresent()){

            Customer editThisCustomer = findCustomerById.get();
            editThisCustomer.setFirstName(customer.getFirstName());
            editThisCustomer.setLastName(customer.getFirstName());
            editThisCustomer.setAddresses(customer.getAddresses());

            customerRepository.save(editThisCustomer);

            logger.info("Customer was Successfully Updated");
            return editThisCustomer;
        }
        logger.error("Unsuccessful Attempt to edit. Customer not found");
        return null;
    }

    //delete customer by Id
    public void deleteCustomer(Long customerId){
        confirmCustomer(customerId);
        logger.info("Customer has Successfully been Deleted");
        customerRepository.deleteById(customerId);
    }


}
