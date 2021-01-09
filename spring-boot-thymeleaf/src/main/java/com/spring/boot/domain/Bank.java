package com.spring.boot.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MD. Zakir Hossain on 08/01/2021.
 */

@Entity
@Table(name = "BANK")
@Getter
@Setter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_name")
    private String bankName;  
    
}
