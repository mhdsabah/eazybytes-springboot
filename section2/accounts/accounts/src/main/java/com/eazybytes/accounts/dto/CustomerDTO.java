package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name cannot be empty or null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email should be a valid value")
    private String email;

    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be a 10-digit number")
    private String mobileNumber;
    private AccountsDTO accountsDTO;
}
