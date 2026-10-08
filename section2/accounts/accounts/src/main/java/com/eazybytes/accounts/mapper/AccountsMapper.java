package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDTO maptoAccountsDTO(Accounts accounts, AccountsDTO accountsDTO) {
        // Mapping logic from Accounts to AccountsDTO
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }

    public static Accounts maptoAccounts(AccountsDTO accountsDTO, Accounts accounts) {
        // Mapping logic from AccountsDTO to Accounts
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }

}
