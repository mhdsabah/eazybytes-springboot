package com.eazybytes.accounts.service.impl;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void CreateAccount(CustomerDTO customerDTO) {
        // Implementation for creating an account
        
        Customer customer = CustomerMapper.maptoCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customer.getMobileNumber() + " already exists.");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }


    /**
     *
     * @param mobileNumber
     * @return Account details based on a given MobileNumber
     */
    @Override
    public CustomerDTO fetchAccount(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = CustomerMapper.maptoCustomerDTO(customer,new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.maptoAccountsDTO(accounts,new AccountsDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        
        if (accountsDTO != null){
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","accountNumber",accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.maptoAccounts(accountsDTO,accounts);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","customerId",customerId.toString())
            );
            CustomerMapper.maptoCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated = true;

        }

        return isUpdated;

    }

    @Override 
    public boolean deleteAccount(String mobileNumebr){
        Customer customer = customerRepository.findByMobileNumber(mobileNumebr).orElseThrow(
                ()->new ResourceNotFoundException("Customer","mobileNumber",mobileNumebr)
        );
        accountsRepository.deleteById(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;

    }


}
