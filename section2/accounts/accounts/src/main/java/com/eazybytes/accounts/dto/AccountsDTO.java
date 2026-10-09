package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDTO {

    @NotEmpty (message = "Account number cannot be empty or null")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be a 10-digit number")
    private Long accountNumber;

    @NotEmpty (message = "Account type cannot be empty or null")
    private String accountType;

    @NotEmpty (message = "Branch address cannot be empty or null")
    private String branchAddress;
}
