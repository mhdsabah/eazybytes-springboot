package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.CustomerDetailsDTO;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO maptoCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        // Mapping logic from Customer to CustomerDTO
        
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer maptoCustomer(CustomerDTO customerDTO, Customer customer) {
        // Mapping logic from CustomerDTO to Customer
        
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

    public static CustomerDetailsDTO mapToCustomerDetailsDTO(Customer customer, CustomerDetailsDTO customerDetailsDTO){
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setMobileNumber(customerDetailsDTO.getMobileNumber());
        return customerDetailsDTO;
    }


}
