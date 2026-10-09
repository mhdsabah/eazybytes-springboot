package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eazybytes.accounts.service.IAccountsService;
import com.eazybytes.accounts.constants.AccountsConstants;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor 
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){
        iAccountsService.CreateAccount(customerDTO);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDTO customerDTO =  iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);

    }


}
