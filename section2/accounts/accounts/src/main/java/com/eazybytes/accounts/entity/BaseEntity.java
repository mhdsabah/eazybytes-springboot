package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//this indicates to the spring datajpa framework that this
//class is going to act as a superclass for all my entities where we extend base entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
//      `created_at` date NOT NULL,
//            `created_by` varchar(20) NOT NULL,
//  `updated_at` date DEFAULT NULL,
//            `updated_by` varchar(20) DEFAULT NULL

//  This field wont be updated
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;


}
