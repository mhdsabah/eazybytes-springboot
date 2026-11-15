package com.eazybytes.accounts.service;


import com.eazybytes.accounts.dto.CustomerDetailsDTO;

//@Service
public interface ICustomersService {
    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
