package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
@Data
public class CustomerDTO {

    @Schema(
            description = "Name of the customer", example = "Sabah"
    )
    @NotEmpty(message = "Name cannot be empty or null")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "sabah@gmail.com"
    )
    @NotEmpty(message = "Email cannot be empty or null")
    @Email(message = "Email should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "9988776655"
    )
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be a 10-digit number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDTO accountsDTO;
}
