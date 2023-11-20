package com.thecoalition.bankingApi.service;


//import com.thecoalition.bankingApi.model.Account;
import com.thecoalition.bankingApi.model.Customer;
import com.thecoalition.bankingApi.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRespository customerRepository;

    @Autowired
    AddressService addressService;

    @Autowired
//    Account account;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Iterable<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId){
        return customerRepository.findById(customerId);
    }

    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }

    public Customer editCustomer(Customer customer, Long customerId){
        Optional<Customer> findByResult = customerRepository.findById(customerId);


            Customer customerToEdit = findByResult.get();

            customerToEdit.setFirstName(customer.getFirstName());
            customerToEdit.setLastName(customer.getLastName());
            customerToEdit.setAddress(customer.getAddress());


            customerRepository.save(customerToEdit);

            return customerToEdit;
    }
    public Iterable<Customer> getCustomerByAccountId(Long accountId){
        Customer customerToGetAccount = new Customer();
      return customerRepository.findAccountsByAccountId(accountId);
   }

}
