package com.eazybytes.accounts.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

//   works without column name, but for future reference
    @Column(name="customer_id")
    private Long customerId;
//    not using strategy for generation type coz
//    acc number are generally long (15 dig)
    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    private String accountType;
    private String branchAddress;

}
