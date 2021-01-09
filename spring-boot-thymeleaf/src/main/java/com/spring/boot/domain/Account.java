package com.spring.boot.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by MD. Zakir Hossain on 09/01/2021.
 */

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "current_balance")
    private Double currentBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;


}
