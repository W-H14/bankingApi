package com.thecoalition.bankingApi.service;

import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRepository;
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

    //add customer
    public Customer createCustomer(Customer customer){
    return customerRepository.save(customer);
    }
    //get all customers
    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //get customers by AccountID Iterable<>
    public Iterable<Customer> getCustomerByAccountId(Long accountId){
        Customer findCustomerByAccount = new Customer();
        return customerRepository.findCustomersByAccountId(accountId);
    }


    //get customers by ID Optional<>
    public Optional<Customer> getCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }

    //edit customers use Optional<> and a loop to edit specifics
    public Customer editCustomer(Customer customer, Long customerId){
        Optional<Customer> findCustomerById = customerRepository.findById(customerId);

        if(findCustomerById.isPresent()){
            Customer editThisCustomer = findCustomerById.get();
            editThisCustomer.setFirstName(customer.getFirstName());
            editThisCustomer.setLastName(customer.getFirstName());
            editThisCustomer.setAddresses(customer.getAddresses());

            customerRepository.save(editThisCustomer);
            return editThisCustomer;
        }
        return null;
    }

    //delete customer by Id
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }

}
