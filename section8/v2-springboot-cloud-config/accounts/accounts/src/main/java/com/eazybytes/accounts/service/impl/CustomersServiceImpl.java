package com.eazybytes.accounts.service.impl;


import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.dto.CardsDTO;
import com.eazybytes.accounts.dto.CustomerDetailsDTO;
import com.eazybytes.accounts.dto.LoansDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomersService;
import com.eazybytes.accounts.service.client.CardsClient;
import com.eazybytes.accounts.service.client.LoansClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsClient cardsClient;
    private LoansClient loansClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO CustomerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        CustomerDetailsDTO.setAccountsDTO(AccountsMapper.maptoAccountsDTO(accounts, new AccountsDTO()));

        ResponseEntity<LoansDTO> LoansDTOResponseEntity = loansClient.fetchLoanDetails(mobileNumber);
        CustomerDetailsDTO.setLoansDTO(LoansDTOResponseEntity.getBody());

        ResponseEntity<CardsDTO> CardsDTOResponseEntity = cardsClient.fetchCardDetails(mobileNumber);
        CustomerDetailsDTO.setCardsDTO(CardsDTOResponseEntity.getBody());

        return CustomerDetailsDTO;

    }
}
