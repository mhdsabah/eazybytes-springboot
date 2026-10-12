package com.eazybytes.cards.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardsDTO {

    @NotEmpty(message = "Mobile numeber cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be a 10-digit number")
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;

}
