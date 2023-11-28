package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.handler.exceptions.CustomerNotFoundException;
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

    public void confirmCustomer(Long customerId) throws CustomerNotFoundException{
        if(!customerRepository.existsById(customerId)){
            logger.error("Customer Not confirmed");
            throw new CustomerNotFoundException("Customer with id '" + customerId + "' is not found");
        }
    }

    //add customer
    public Customer createCustomer(Customer customer) throws Exception{
    logger.info("successfully created Customer");
    return customerRepository.save(customer);
    }



    //get all customers
    public Iterable<Customer> getAllCustomers() throws CustomerNotFoundException{
    logger.info("successfully retrieved all customers");
        Iterable<Customer> customers = customerRepository.findAll();

        if(!customers.iterator().hasNext()){
        throw new CustomerNotFoundException("“error fetching accounts");
    }
        return customers;
    }

    //get customers by AccountID Iterable<>
    public Iterable<Customer> getCustomerByAccountId(Long accountId){
        Customer findCustomerByAccount = new Customer();
        logger.info("Successfully retrieved customer by account Id");
        if(accountId == null){
            throw new CustomerNotFoundException("Error Fetching Account");
        }

        return customerRepository.findCustomersByAccountId(accountId);
    }


    //get customers by ID Optional<>
    public Optional<Customer> getCustomerById(Long customerId) throws CustomerNotFoundException{
        logger.info("Successfully retrieved customer by customer ID");
        if (customerId == null) {
            throw new CustomerNotFoundException("Error fetching account");
        }
        return customerRepository.findById(customerId);
    }

    //edit customers use Optional<> and a loop to edit specifics
    public Customer editCustomer(Customer customer, Long customerId) throws CustomerNotFoundException {
        Optional<Customer> findCustomerById = customerRepository.findById(customerId);

        if(findCustomerById.isPresent()){

            Customer editThisCustomer = findCustomerById.get();
            editThisCustomer.setFirstName(customer.getFirstName());
            editThisCustomer.setLastName(customer.getFirstName());
            editThisCustomer.setAddresses(customer.getAddresses());

            customerRepository.save(editThisCustomer);

            logger.info("Customer was Successfully Updated");
            return editThisCustomer;
        } else {
            logger.error("Unsuccessful Attempt to edit. Customer not found");
           throw new CustomerNotFoundException("Error updating Customer with id " + customerId );
        }
    }

    //delete customer by Id
    public void deleteCustomer(Long customerId){
        confirmCustomer(customerId);
        logger.info("Customer has Successfully been Deleted");
        customerRepository.deleteById(customerId);
    }


}
