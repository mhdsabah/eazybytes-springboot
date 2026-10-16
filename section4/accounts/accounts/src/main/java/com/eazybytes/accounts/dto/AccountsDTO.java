package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts ",
        description = "Schema to hold Account information"
)
public class AccountsDTO {
    @Schema(
            description = "Account number of Eazybank account",example = "1234567899"
    )
    @NotEmpty (message = "Account number cannot be empty or null")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Account number must be a 10-digit number")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazybank account", example = "Savings"
    )
    @NotEmpty (message = "Account type cannot be empty or null")
    private String accountType;

    @Schema(
            description = "Branch address details of Eazybank account",example = "123 Main St.,NY"
    )
    @NotEmpty (message = "Branch address cannot be empty or null")
    private String branchAddress;
}
