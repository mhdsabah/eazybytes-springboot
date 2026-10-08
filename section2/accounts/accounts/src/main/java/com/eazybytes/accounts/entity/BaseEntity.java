package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//this indicates to the spring datajpa framework that this
//class is going to act as a superclass for all my entities where we extend base entity
@MappedSuperclass
public class BaseEntity {
//      `created_at` date NOT NULL,
//            `created_by` varchar(20) NOT NULL,
//  `updated_at` date DEFAULT NULL,
//            `updated_by` varchar(20) DEFAULT NULL

//  This field wont be updated
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;


}
