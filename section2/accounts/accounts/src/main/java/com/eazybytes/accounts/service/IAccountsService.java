package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountsService {


    /**
     * Create a new account for the given customer.
     *
     * @param customerDTO the customer data transfer object containing customer details
     */
    void CreateAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccount(String mobileNumber);

}
